package com.cisco.webex.provisioning.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.tracking.ThreadLocalTrackingManager;
import com.cisco.tracking.Tracking;
import com.cisco.webex.pml.ProvisioningException;
import com.cisco.webex.pml.input.provisioning.site.Site;
import com.cisco.webex.pml.output.provisioning.ProvResponseData;
import com.cisco.webex.pml.util.common.HttpClientCommon;
import com.cisco.webex.pml.util.common.accesstoken.AccessTokenStore;
import com.cisco.webex.pml.util.provisioning.ErrorKey;
import com.cisco.webex.pml.util.provisioning.ProvProperties;
import com.cisco.webex.pml.util.provisioning.ProvisioningErrors;
import com.cisco.webex.pml.util.provisioning.ProvisioningErrors.ProvisioningError;
import com.cisco.webex.provisioning.site.RenameResultsErrorDetails;
import com.cisco.webex.provisioning.site.RenameResultsRequestBean;
import com.cisco.webex.pml.output.provisioning.Error;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class SiteRenameUtil {
	
	public static final Logger log = LoggerFactory.getLogger(SiteRenameUtil.class);
	public static final String PRODUCT_NAME_WX="WX";
	public static final String PRODUCT_NAME_CLOUD="CLOUD";
    public static final String FOLDER_AUDIO="audio";
    public static final String FOLDER_CONFERENCE="conferencing";
    public static final String FOLDER_STORAGE="storage";
    public static final String FOLDER_CMR="cmr";
    public static final String STATUS_PROVISIONED="PROVISIONED";
    public static final String STATUS_ERROR="ERROR";
    private static final long THREE_MIN = 2000;//180000;
	
	public static boolean isValidInput(String val){
		
		boolean bVaildInput = false;
		Gson gson = new Gson();
		RenameResultsRequestBean jsonInput = null;
		if(  null != val && !val.isEmpty() ){
			try {
				jsonInput = gson.fromJson(val, RenameResultsRequestBean.class);
				if( (null != jsonInput) && (null != jsonInput.getOldSiteUrl()) && (null != jsonInput.getNewSiteUrl()) && validateSiteUrl(jsonInput.getOldSiteUrl()) && validateSiteUrl(jsonInput.getNewSiteUrl()))
					bVaildInput = true;
	    	}
		    catch(JsonSyntaxException e) {  
		    	return false;
		    }
		}
		return bVaildInput;
	}
	
	public static boolean validateSiteUrl(String url){
		String regex = "([A-Za-z0-9-]+(-[A-Za-z0-9-]+)*.){3,}[A-Za-z0-9-]+(-[A-Za-z0-9-]+)*";
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(url);
		return m.matches();
	}

	public static String getAtlasCallbackUrl(String orderId) {
		// TODO Auto-generated method stub
		String atlasEndPoint = ProvProperties.getProp(ProvProperties.PROP_ATLAS_API_ENDPOINT);
		return atlasEndPoint + "/v1/orders/"+orderId+"/status";
	}

	public static List<String> getServiceItemsList(String serviceItems) {
		List<String> serviceItemsList = new ArrayList<String>();
		StringTokenizer strTok =  new StringTokenizer(serviceItems,","); 
	    while (strTok.hasMoreTokens()) {
	        	serviceItemsList.add(strTok.nextToken().trim().toUpperCase()); 
	    }
		return serviceItemsList;
	}

	public static ProvResponseData constructResponseData(RenameResultsRequestBean renameResult, String serviceItems, Site site, String trackingID) {
		String status = "";
		String oldSiteUrl = renameResult.getOldSiteUrl();
		String newSiteUrl = renameResult.getNewSiteUrl();
		boolean hasErrors = false;

		// Status will be set to ERROR if the result from MBS comes with errors, otherwise it will be set to PROVISIONED
		if(null != renameResult.getErrors() && renameResult.getErrors().size() >=1 ){
			status = STATUS_ERROR;
			hasErrors = true;
		}else
			status = STATUS_PROVISIONED;

		if( null == oldSiteUrl || null == newSiteUrl || null == serviceItems)
			return null;


		ProvResponseData response = new ProvResponseData();
		List<Error> errors = null;
		int typeOfSite = 0;
		boolean isCIUnifiedSite = false;
		String siteUUID = "";
		String siteId = "";
		try {
			response.setProductStatus(status);

			List<String> serviceItemsList = new ArrayList<String>();
			if( null != serviceItems && !serviceItems.isEmpty())
				serviceItemsList = getServiceItemsList(serviceItems);

			for(String serviceItemName:serviceItemsList){
				switch (serviceItemName){
				case "MC":
				case "TC":
				case "SC":
				case "EC":
				case "EE":
					response.addServiceStatus(FOLDER_CONFERENCE, PRODUCT_NAME_WX, serviceItemName, oldSiteUrl, status);
					break;
				case "WEBEX":
				case "VOIPONLY":
				case "TSP":
				case "CCASP":
				case "CCAUSER":
					response.addServiceStatus(FOLDER_AUDIO,PRODUCT_NAME_WX, serviceItemName, oldSiteUrl, status);
					break;
				case "CMR":
					response.addServiceStatus(FOLDER_CMR, PRODUCT_NAME_WX, serviceItemName, oldSiteUrl, status);
					break;
				case "ST":
					response.addServiceStatus(FOLDER_STORAGE, PRODUCT_NAME_CLOUD, serviceItemName, null, status);
					break;
				default:
					log.info("Unknown Service Item");
					break;
				}
			}

			if( null != site && null != site.getMetaData()) {
				typeOfSite = site.getMetaData().getSupportTypeOfSite();
				isCIUnifiedSite = (typeOfSite == 2 ? true : false) ;

				siteUUID =  site.getMetaData().getNewSiteUUID();
				siteId =  site.getSiteID();
			}else{
				Site siteData = WAPIUtil.getSiteSelectedDataByUrl(newSiteUrl, "siteID:metaData"); // Call WAPI to get siteId, siteUUId, site type ( CI or Non CI ).				
				if (siteData == null){
					throw new ProvisioningException("Invalid site url", ProvisioningErrors.getError(ErrorKey.PROV_NO_SUCH_SITE_URL));
				}
				typeOfSite = siteData.getMetaData().getSupportTypeOfSite();
				isCIUnifiedSite = (typeOfSite == 2 ? true : false) ;
				siteUUID = siteData.getMetaData().getNewSiteUUID();
				siteId = siteData.getSiteID();
			}
		} catch (ProvisioningException e) { // in case of any exceptions, add error to the response to Atlas
			// TODO Auto-generated catch block
			log.info("Exception in construct response data: " + e.getMessage());
			if( errors == null){
				errors = new ArrayList<> ();
			}
			if( null != e && null != e.getProvisioningError()){
				ProvisioningError provErr = e.getProvisioningError();
				Error error = new Error(newSiteUrl, provErr.getDescription(), provErr.getCode());
				errors.add(error);
			}

			response.setErrors(errors);
		}
		
			log.info("isCIUnifiedSite? " + isCIUnifiedSite + " , siteUUID: " + siteUUID + "  , siteId: " + siteId + ", Contains Errors? " + hasErrors);

			response.addSiteProperties(oldSiteUrl, newSiteUrl, siteId, isCIUnifiedSite, siteUUID);
			 //trackingId in ProvResponseData should be Provisioner tracking ID
			if (!StringUtils.isEmpty(trackingID)) {
				response.setTrackingId(trackingID);
			} else {
				Tracking tracking = ThreadLocalTrackingManager.getInstance().get(null, false);
				response.setTrackingId(tracking == null ? null : tracking.getTrackingId());
			}
			String upstreamTrackingID = "";
			if(null != renameResult.getTrackingID() && !renameResult.getTrackingID().isEmpty()){
				upstreamTrackingID = renameResult.getTrackingID();
			} 
			/* If the result from MBS has any errors, forward them to Atlas. 
			 * Here's the agreed upon error format with Atlas:
			 * "errors": [
		    {
		      "errorCode": 590005,
		      "description": "Error processing users in CI, upstreamTrackingID=NA_52bc1465-2545-4241-ac99-17310667812b, failedUserCount=100, failedUsers=id1,id2,id3 "
		    },
		    {
		      "errorCode": 590006,
		      "description": "Internal error from MBS, upstreamTrackingID=NA_5354f85b-be0b-43b0-8315-41938e1d8784"
		    }
		  ]
			 */
			if(hasErrors){
				List<RenameResultsErrorDetails> renameErrors = renameResult.getErrors();

				errors = new ArrayList<> ();
				for(RenameResultsErrorDetails renameError:renameErrors){
					int code = Integer.valueOf(renameError.getErrorCode());
					String description = "";
					if(null != renameError.getDescription())
						description = renameError.getDescription();
					if(null != renameError.getNumberOfFailedUsers())
						description += (",failedUserCount="+renameError.getNumberOfFailedUsers());
					if(null != renameError.getUserIdList() && !renameError.getUserIdList().isEmpty())
						description += (",failedUsers=" + renameError.getUserIdList());
					if(upstreamTrackingID.length() > 0)
						description += (" ,upstreamTrackingID="+upstreamTrackingID);
					errors.add(new Error(null, description, code));
				}
				response.setErrors(errors);
			}	
		return response;

	}
	
	// remove site rename records from the DB immediately, do not wait for the default 30 day period to cleanup old site url records.
	public static void expireOldSiteUrlNow(String siteUrl) throws ProvisioningException{
		try{
			int siteId = WAPIUtil.getSiteIdByUrl(siteUrl); //WAPI call to remove old records requires SiteId, get siteId from DB based on the new site url ( which was already renamed).
			WAPIUtil.removeSiteRenameRecordsBySiteID(String.valueOf(siteId)); // call WAPI to cleanup old site url records immediately
		}catch(ProvisioningException e){
			log.info("Exception in expireOldSiteUrlNow:: " + e.getMessage());
			throw e;
		}
	}
	
	// Send response to Atlas
	public static void sendSiteRenameResponseToAtlas(String callbackUrl, String responseData) throws ProvisioningException{

		int responseStatusCode = 0;
		int count = -1;
        boolean isResultSent = false;
		CloseableHttpResponse response = null;
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(HttpClientCommon.K_HEADER_CONTENT_TYPE, HttpClientCommon.V_HEADER_CONTENT_TYPE_JSON);
		String accessToken = AccessTokenStore.getAccessToken();
		if (accessToken != null)
			headerMap.put(HttpClientCommon.K_AUTHORIZATION, "Bearer " + accessToken);	
        
		//set trackingId to headerMap for Atlas Callback
	    Tracking tracking = ThreadLocalTrackingManager.getInstance().get(null, false);
	    headerMap.put("TrackingID", tracking == null ? null : tracking.getTrackingId());
	    
        while (!(isResultSent) && (count < 10)) {
        	count++;
			try {
				log.info("Sending SiteRename response to : " + callbackUrl + " Response Data: " + responseData );
				response = HttpClientCommon.doPost(callbackUrl, responseData, headerMap);
				if(null != response && null != response.getStatusLine()) {
					responseStatusCode = response.getStatusLine().getStatusCode();
					log.info("Response status code from Atlas: " + responseStatusCode);
					
					if (responseStatusCode== HttpStatus.SC_ACCEPTED || responseStatusCode == HttpStatus.SC_OK
	                        || responseStatusCode == HttpStatus.SC_NO_CONTENT) {
	                    isResultSent = true;
	                    break;
	                } else if (responseStatusCode == HttpStatus.SC_BAD_REQUEST || responseStatusCode == HttpStatus.SC_NOT_FOUND) { 
	                    log.debug("Got wrong status code [{}] from Atlas callback Url [{}].", responseStatusCode, callbackUrl);
	                    ProvisioningError error = ProvisioningErrors.getError(ErrorKey.PROV_FAILED_CALLBACK_URL);
	        			throw new ProvisioningException("Got wrong status code from Atlas callback Url", error);
	                }
	                else if (responseStatusCode > 300) {
	                    log.error("Failed in sening ResponseData with status code [{}] to Atlas callback Url [{}].", responseStatusCode, callbackUrl);
	                    log.error("Sleep for three mimutes, then retry [{}]-th ...", count);
	                    try {
	                        Thread.sleep(THREE_MIN);	                    }
	                    catch (InterruptedException e) {
	                        log.error("Thread sleep was interrupted: [{}].", e.getMessage());
	                    }
	                }
				}
			} catch (IOException | URISyntaxException e) {
				log.error("Exception in sending response to Atlas: " + e.getMessage());
				ProvisioningError error = ProvisioningErrors.getError(ErrorKey.PROV_MISS_CALLBACK_URL); 
	            throw new ProvisioningException(e.getMessage(), error);
			}
        } //while
        
        if (!isResultSent && count == 10) {
            log.error("The [{}]-th attempt sendingResponse data to Atlas callback Url failed. ", count);
            throw new ProvisioningException("Received HTTP status code greater than 300", ProvisioningErrors.getError(ErrorKey.PROV_FAILED_SENDING_REPONSE_ATLAS));
        }
	}
}
