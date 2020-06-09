package com.cisco.webex.provisioning.site;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.tracking.ThreadLocalTrackingManager;
import com.cisco.tracking.Tracking;
import com.cisco.webex.pml.ProvisioningException;
import com.cisco.webex.pml.impl.task.provisioning.ProvisioningTaskImpl;
import com.cisco.webex.pml.impl.task.provisioning.wapi.SiteRenameRec;
import com.cisco.webex.pml.input.provisioning.site.AltSiteURLs;
import com.cisco.webex.pml.input.provisioning.site.MetaData;
import com.cisco.webex.pml.input.provisioning.site.Site;
import com.cisco.webex.pml.output.provisioning.ProvResponseData;
import com.cisco.webex.pml.util.common.HttpClientCommon;
import com.cisco.webex.pml.util.common.accesstoken.AccessTokenInfo;
import com.cisco.webex.pml.util.common.accesstoken.MBSTokenHelper;
import com.cisco.webex.pml.util.provisioning.ErrorKey;
import com.cisco.webex.pml.util.provisioning.ProvProperties;
import com.cisco.webex.pml.util.provisioning.ProvisioningErrors;
import com.cisco.webex.pml.util.provisioning.ProvisioningErrors.ProvisioningError;
import com.cisco.webex.provisioning.apiframework.ProvisionerApiException;
import com.cisco.webex.provisioning.services.CheckSiteStatusService;
import com.cisco.webex.provisioning.util.SiteRenameUtil;
import com.cisco.webex.provisioning.util.WAPIUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONObject;

/* 
 * User can rename an exsting site 
 *  
 * 
 * alex ng
 */
public class SiteRenameUpdateUrl {
	
	public 	final  Logger logger = LoggerFactory.getLogger(SiteRenameUpdateUrl.class);
	private final  String interval = ProvProperties.getProp(ProvProperties.PROP_DNS_LOOKUP_TIME_INTERVAL);
    private final  String maxWaitTime = ProvProperties.getProp(ProvProperties.PROP_MAX_DNS_LOOKUP_TIME);
    private final  String enableDNSLookup = ProvProperties.getProp(ProvProperties.PROP_ENABLE_DNS_LOOKUP);
    private final  long waitForDataReplication = 5000;	// 5 seconds
	
    private  Integer	jumpPageType = 1;
    private  String 	enableDNS 	 = "true";
   
    public boolean updateNewSiteURL(UriInfo uriInfo, Site site, String newSiteName, String newSiteURL,
				String atlasCallBackURL, String serviceItems, boolean isImmediate, String trackingID) throws ProvisioningException {
    	
    	try {
    		
    		// First check if site rename allowed has reached the limit
    		String strSiteId = site.getSiteID();
    		checkHasReachedSiteRenameLimit(strSiteId);      
            
    		// Get the type of site: standard=0, portal=1, CI=2
    		String oldSiteUrl = site.getMetaData().getSiteFullURL();
    		int typeOfSite = site.getMetaData().getSupportTypeOfSite();
    		int timezoneid = site.getMetaData().getTimeZoneID();
    		String billingMode = site.getMetaData().getBillingMode();
    		
            // Build the required parameters for site rename to work    	
        	MetaData metaData = new MetaData(); 
        	metaData.init();
        	metaData.setSiteName(newSiteName);
        	metaData.getMainSiteURL().setSiteName(newSiteName);
        	metaData.setSiteFullURL(newSiteURL);
        	metaData.setSiteJumpPageType(jumpPageType);
        	metaData.setSupportTypeOfSite(typeOfSite);
        	metaData.setTimeZoneID(timezoneid);
        	metaData.setBillingMode(billingMode);
        	metaData.setEnableDNSCreation(enableDNS);
        	metaData.setSiteUUID(site.getMetaData().getSiteUUID());
        	metaData.setNewSiteUUID(site.getMetaData().getNewSiteUUID());
        	if (site.getMetaData().getCiOrgId() !=null) {
        		metaData.setCiOrgId(site.getMetaData().getCiOrgId());
        	}
        	
        	//US40623 - Rename Alternate SiteUrls with new siteName
        	AltSiteURLs newAltSiteURLs = SiteOptionsUtil.renameAlternateSiteURLs(site, strSiteId, newSiteName);
        	metaData.setAltSiteURLs(newAltSiteURLs);
        	site.setMetaData(metaData);
        	
        	String xmlString = ProvisioningTaskImpl.getXMLfromSite(site);
        	
        	boolean bUpdateSuccess = WAPIUtil.UpdateSiteData(strSiteId, xmlString);
        	if (!bUpdateSuccess) {
                throw new ProvisionerApiException(ProvisioningErrors.getError(ErrorKey.PROV_MODIFY_SITE_FAILURE));
            }
        	else
        		logger.debug("Site: "+oldSiteUrl+" is successfully renamed to: " + newSiteURL);
        	
        	/*
        	 * Site rename by super admin is completed. Create a thread process:
        	 * - wait for site url to be resolved
        	 * - after site url is rsolved, call MBS API to further rename site at CI
        	 */
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new CheckDNSAvail(uriInfo, site, newSiteName, newSiteURL, oldSiteUrl, atlasCallBackURL,	serviceItems, isImmediate, trackingID));
        
            logger.debug("Main process will end here and send 202 response to Atlas.");
            return bUpdateSuccess;
    	} 
    	catch (ProvisioningException e) {
    		throw new ProvisionerApiException(e);
    	}
    }
    
    /*
     * Check if site URL is resolved in DNS
     */
    private class CheckDNSAvail implements Runnable {
    	
    	private final UriInfo uriInfo;
    	private final Site site;
    	private final String newSiteURL;
    	private final String oldSiteURL;
    	private final String newSiteName;
    	private final String atlasCallBackURL;
    	private final String serviceItems;
    	private final boolean isImmediate;
    	private String trackingID;

        private CheckDNSAvail(UriInfo uriInfo, Site site, String newSiteName, String newSiteURL, String oldSiteURL,
        				String atlasCallBackURL, String serviceItems, boolean isImmediate, String trackingID) {
        	this.uriInfo = uriInfo;
            this.site = site;
            this.newSiteName = newSiteName;
            this.newSiteURL = newSiteURL;
            this.oldSiteURL = oldSiteURL;
            this.atlasCallBackURL = atlasCallBackURL;
            this.serviceItems = serviceItems;
            this.isImmediate = isImmediate;
            this.trackingID = trackingID;
        }
        
    	public void run() {
            
    		// Check if new site url is ready available in DNS. Call Atlas back url
            List<String> newSiteUrlList = new ArrayList<String>();
            newSiteUrlList.add(newSiteURL);
            
            int supportTypeOfSite = site.getMetaData().getSupportTypeOfSite();
            String billingMode = site.getMetaData().getBillingMode();
            
            // If tracking id is empty, generate one.
	        if (StringUtils.isEmpty(trackingID)) {
	        	Tracking tracker = ThreadLocalTrackingManager.getInstance().get(null, false);
	        	if(null!=tracker) {
	        		trackingID = tracker.getTrackingId();
	        	}
	        }
	        logger.info("Setting tracking id from the calling thread as : " + trackingID);
            
            try {
            	if ( supportTypeOfSite == 0)	// Is a standard site 
            	{
            		if (site.getMetaData().getCiOrgId() == null || site.getMetaData().getCiOrgId().isEmpty()) 
            		{
            			// NOT a CI linking site. Make Atlas Callback without calling MBS
            			if (enableDNSLookup != null && enableDNSLookup.equalsIgnoreCase("true")) 
            			{ 
            				waitForDNS(newSiteURL);
        				}

        				// Site URL is resolved now
        				logger.debug("Site rename: Standard non CI linking new site URL is resolved: " + newSiteURL);
        				constructAtlasHappyPathResponse(site, atlasCallBackURL, newSiteURL, oldSiteURL, trackingID, serviceItems, isImmediate);
            		} 
            		else { // Is a CI linking site
            			// For CI linking site, always wait for DNS to be resolved
            			waitForDNS(newSiteURL);
            			logger.debug("Site rename: Standard CI linking new site URL is resolved: " + newSiteURL);
            			
            			// Site data must be replicated to WEB DB before calling site admin site rename API
                		//if (isSiteDataReplicated(newSiteURL)) {
                			new SiteRenameUpdateUrl().prepareSiteRenameAtCI(uriInfo, site, newSiteName, newSiteURL, oldSiteURL, 
                								atlasCallBackURL, serviceItems, isImmediate, trackingID);
                		//}
            		}
            	} 
            	else if (supportTypeOfSite == 2) {
            		// For CI sites, always wait for DNS to be resolved. No need to check flag
            		waitForDNS(newSiteURL);
            		logger.debug("Site rename: CI new site URL is resolved: " + newSiteURL);
            		
            		try {
            			if (billingMode != null && !billingMode.isEmpty())	// only need sleep for MCOL site
            				Thread.sleep(waitForDataReplication);
                    }
                    catch (InterruptedException e) {
                        logger.debug("Thread Interrupted Unexpectedly: " + e.getMessage());
                    }
            		
            		// Site data must be replicated to WEB DB before calling site admin site rename API
            		//if (isSiteDataReplicated(newSiteURL)) {            			
            			// Ready to call MBS/admin site rename API
            			new SiteRenameUpdateUrl().prepareSiteRenameAtCI(uriInfo, site, newSiteName, newSiteURL, oldSiteURL, 
            									atlasCallBackURL, serviceItems, isImmediate, trackingID);
            		//}
            	}
            }
            catch (ProvisioningException e) {
    			logger.error("Site rename: Exception Error returned from MBS and/or CI API: " + e.getMessage());
				constructAtlasErrorResponse(site, atlasCallBackURL, newSiteURL, oldSiteURL, trackingID, serviceItems, e);
        	}
		}
    }
    
    /*
     * Wait for new site url to be replicated to WEB DB
     */
    private boolean isSiteDataReplicated(String newSiteUrl) throws ProvisioningException 
    {
    	List<String> siteUrls = new ArrayList<String>();
		siteUrls.add(newSiteUrl);            		
		double maxWaitTimeInSec = Double.valueOf(maxWaitTime) * 60;
		double intervalInSec = Double.valueOf(interval);
		if (new CheckSiteStatusService().siteLookUp(siteUrls, maxWaitTimeInSec, intervalInSec)) {
			logger.debug("Site rename: new site URL is replicated to WEB DB: " + newSiteUrl);
			return true;
		}
		else {
			logger.error("Site rename: wait timed out. new site URL is NOT replicated to WEB DB: " + newSiteUrl);
			return false;
		}
    }
    
    /*
     * Wait for new site url to be resolved in DNS
     */
    private boolean waitForDNS(String newSiteUrl) throws ProvisioningException 
    {
        long maxWaitTimeInMilli = 0;
        long intervalInMilli = 0;

        logger.debug("The siteUrl is with action NEW:" + newSiteUrl);
        if (newSiteUrl.isEmpty())
            return false;

        try {
            maxWaitTimeInMilli = Long.valueOf(maxWaitTime) * 60 * 1000;
            intervalInMilli = Long.valueOf(interval) * 1000;
        }
        catch (NumberFormatException e) {
            logger.error("NumberFormatException: Please check maxWaitTime and interval values in congifuration file");
            throw new ProvisioningException("NumberFormatException: Please check maxWaitTime and interval values in congifuration file",
                    ProvisioningErrors.getError(ErrorKey.PROV_INVALID_NUMBER_FORMAT));
        }

        // Do DNS LookUP, if it returns true => site have DNS
        if (getDnsLookUp(maxWaitTimeInMilli, intervalInMilli, newSiteUrl)) {
            return true;
        }
        else {
            ProvisioningError result = ProvisioningErrors.getError(ErrorKey.PROV_DNS_CREATION_FAIL);
            logger.error("Failed to check site URL existence in DNS: " + result.getCode() + "Description: " + result.getDescription());
        }
        return false;
    }
    
    /*
     * Ping site URL
     */
    private boolean getDnsLookUp(long maxWaitTimeInMilli, long intervalInMilli, String newSiteUrl) {
        long startTime = System.currentTimeMillis();
        // we set this flag to true if all siteUrls have corresponding DNS entries, else return
        // false
        boolean isDNSFound = false;

        // we loop until : maxWaitTime is exhausted.
        while (System.currentTimeMillis() - startTime <= maxWaitTimeInMilli) {
            try {
            	long timeRemaining = maxWaitTimeInMilli - System.currentTimeMillis() + startTime;
            	//logger.debug("Check new site URL availability .......... remaining: " + timeRemaining);
                if (checkIfDNSCreated(newSiteUrl)) {
                    isDNSFound = true;
                    break;
                }
                
                if (timeRemaining <= 0) {
                	logger.debug("Check DNS site URL "+newSiteUrl+" availabilty timed out after "+maxWaitTime+" minutes");
                    break;
                }
                long waitTime = intervalInMilli < timeRemaining ? intervalInMilli : timeRemaining;
                Thread.sleep(waitTime);
            }
            catch (InterruptedException e) {
                logger.debug("Thread Interrupted Unexpectedly: " + e.getMessage());
            }
        }
        return isDNSFound;
    }

    /*
     * This method checks if DNS is created for given hostName. If yes returns true, else returns false
     */
    private boolean checkIfDNSCreated(String newSiteUrl) {
        boolean created = false;
        InetAddress hostAddress = null;

        try {
            if (null != (hostAddress = InetAddress.getByName(newSiteUrl))) {
            	logger.debug("Site rename: Ip adress of " + newSiteUrl + " is: " + hostAddress);
                created = true;
            }
        }
        catch (UnknownHostException uhe) {
            //logger.debug("DNS for: " + hostName + " is not found");
        }
        return created;
    }
    
    /*
     * Check if site rename has reached maximum allowed.
     * If so, delete the oldest record and add new one
     */
    private void checkHasReachedSiteRenameLimit(String strSiteId) throws ProvisioningException {
    	
    	String maxSiteRename = ProvProperties.getProp(ProvProperties.PROP_MAX_SITE_RENAME_ALLOWED);
    	int srLimit = 3;	// default to 3 times
    	if (maxSiteRename != null && !maxSiteRename.isEmpty())
    		srLimit = Integer.valueOf(maxSiteRename);
    	
		// Call API to get site rename records
		List<SiteRenameRec> siteRenameRecordList = WAPIUtil.getSiteRenameRecords(strSiteId);
		if (siteRenameRecordList != null)
		{
			int totRecs = siteRenameRecordList.size();
			if (totRecs >= srLimit)
			{
				logger.debug("Site rename has reached the maximum limit allowed. Site id: " + strSiteId + "  Max limit: " + srLimit);
				// The list is in ascending order, oldest record in index 0
				SiteRenameRec ss = siteRenameRecordList.get(0);
				String siteId		= ss.getSiteId(); 
		    	String siteName		= ss.getOldSiteName();
		    	String siteRenameId	= ss.getSiteRenameId();
		    	String deleteDNS	= ss.getOldSiteNameInDNS();
		    	String deleteSRV	= ss.getOldSiteSRVInDNS();
				
				WAPIUtil.removeSiteRenameRecord(siteId, siteName, siteRenameId, deleteDNS, deleteSRV);
				logger.debug("Oldest site rename record has been removed. Site id: " + strSiteId + "  Oldest site name: " + siteName);
			}
		}
    }
    
    /*
     * Construct the MBS/admin API URL and request body
     * MBSAPIUrl=https://<newSiteUrl>/wbxadmin/api/v1/sites/<newSiteName>/actions/rename
     */
    private void prepareSiteRenameAtCI(UriInfo uriInfo, Site site, String newSiteName, String newSiteUrl, String oldSiteUrl,
    						String atlasCallBackURL, String serviceItems, boolean isImmediate, String trackingID)
            throws ProvisioningException {
    	
    	/* 
    	 * Get the order id from Atlas callback url
    	 * Atlas call-back URL: https://atlas-a.wbx2.com/admin/api/v1/orders/1234-adfg-5gerr-aaaa/status 
    	 */
    	String token = "/v1/orders/";
		int idxstart = atlasCallBackURL.indexOf(token) + token.length();   
		int idxend = atlasCallBackURL.indexOf("/status");
		String orderId = atlasCallBackURL.substring(idxstart, idxend);
	
		/*
    	 * Construct callback URL
    	 * BaseURI/v1/renameResults/order-id/serviceItems/serviceItems/MC,WEBEX,CMR,ST
    	 * Default protocol is always http. For DMZ, BTS, ATS and production, must use https
    	 */
		URI uri = uriInfo.getBaseUri();  //http://localhost:8085/provisioning/
		String uriString = uri.toString();
		String defaultProtocolCallBack = ProvProperties.getProp(ProvProperties.PROP_DEFAULT_PROTOCOL_CALLBACK_FOR_MBS);
		if(defaultProtocolCallBack == null)
			uriString = uri.toString().replace("http", "https");
		logger.debug("Base uri: " + uriString);
	     
        String path =  "v1/renameResults/" + orderId + "/serviceItems/" + serviceItems;
        String callback = uriString + path;
		
    	if (isImmediate)
    		callback = callback + "/expiration/immediate/";    	
    	logger.debug("Provisioner callback URL: " + callback);
    	
    	// MBS/admin URL
    	String mbsAPIUrl = ProvProperties.getProp(ProvProperties.PROP_MBS_API_URL);
    	mbsAPIUrl = mbsAPIUrl.replaceAll("newSiteUrl", newSiteUrl);
    	mbsAPIUrl = mbsAPIUrl.replaceAll("newSiteName", newSiteName);
    	logger.debug("MBS/admin end point: " + mbsAPIUrl);
    	logger.debug("Site rename: Old site URL: " + oldSiteUrl + "    New site URL: " + newSiteUrl);
    
    	// Build request body
    	SiteRenameBody srReqBody = new SiteRenameBody();
    	srReqBody.setOldSiteFullUrl(oldSiteUrl);
    	srReqBody.setNewSiteFullUrl(newSiteUrl);
    	srReqBody.setCallbackUrl(callback);
    	String requestBody = new Gson().toJson(srReqBody);
    	
    	triggerSiteRenameAtCI(site, mbsAPIUrl, atlasCallBackURL, requestBody, trackingID, newSiteUrl, oldSiteUrl, serviceItems, isImmediate);
    }
    
    /*
     * After super admin completed site rename and new site URL is accessible,
     * then call MBS API to rename site at CI side.
     */
    private void triggerSiteRenameAtCI(Site site, String mbsAPIUrl, String atlasCallBackURL, String requestBody, String trackingID, 
			String newSiteUrl, String oldSiteUrl, String serviceItems, boolean isImmediate) throws ProvisioningException {

        HttpResponse response = null;
        
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put(HttpClientCommon.K_HEADER_CONTENT_TYPE, HttpClientCommon.V_HEADER_CONTENT_TYPE_JSON);
        headerMap.put(HttpClientCommon.K_HEADER_CHARSET, HttpClientCommon.V_HEADER_CHARSET_UTF8);
        headerMap.put("trackingID", trackingID);
        
        logger.debug("Calling MBS/admin API: " + mbsAPIUrl);
        AccessTokenInfo accessToken = MBSTokenHelper.getIdBrokerAccessTokenInfo();
        if (accessToken != null)
        	headerMap.put(HttpClientCommon.K_AUTHORIZATION, "Bearer " + accessToken.getAccessToken());
        else {
        	ProvisioningError error = ProvisioningErrors.getError(ErrorKey.PROV_SITE_RENAME_PROCESS_FAILED);
            throw new ProvisioningException("Site rename: Failed to get OAuth access token.", error);
        }
        
        // Make HTTP call to MBS API
        try {
        	logger.debug("Sending request data. requestData = [{}]", requestBody.toString());
            response = HttpClientCommon.doPost(mbsAPIUrl, requestBody, headerMap);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.debug("Status code returned from MBS/admin rename API: " + statusCode);
            if (statusCode == 200) {
            	logger.debug("There are 25 or less users. Site rename is completed at CI. Sending response back to Atlas.");
            	// call back url: pass newSiteUrl, serviceItems
            	constructAtlasHappyPathResponse(site, atlasCallBackURL, newSiteUrl, oldSiteUrl, trackingID, serviceItems, isImmediate);
            }
            else if (statusCode == 202) {
            	// DO not send response back to Atlas. MBS/admin will call-back provisioner when job is completed at CI
            	logger.debug("There are more than 25 users. Created a job for CI to process site rename later.");
            }
            else {
            	logger.debug("Internal error returned from MBS/Admin site rename API: " + statusCode);
				ProvisioningError provError = ProvisioningErrors.getError(ErrorKey.PROV_SITE_RENAME_FAILED_AT_MBS_CI_API);
				ProvisioningException provException = new ProvisioningException("Internal error returned from MBS/Admin site rename API: ", provError);
                constructAtlasErrorResponse(site, atlasCallBackURL, newSiteUrl, oldSiteUrl, trackingID, serviceItems, provException);
            }
        }
        catch (IOException | URISyntaxException e) {
        	String result = "Error Exception calling MBS/Admin API: " + e.getMessage();
        	// re-try
        	ProvisioningError error = ProvisioningErrors.getError(ErrorKey.PROV_SITE_RENAME_PROCESS_FAILED);
            throw new ProvisioningException(result, error);
        }
    }
    
    /*
     * When MBS/admin site rename API returns 200, construct a happy path response to Atlas
     */
    private void constructAtlasHappyPathResponse(Site site, String atlasCallBackURL, String newSiteUrl, String oldSiteUrl,
						String trackingID, String serviceItems, boolean isImmediate) {
    	
    	// Construct the response body
    	RenameResultsRequestBean happyResp = new RenameResultsRequestBean();
    	happyResp.setOldSiteUrl(oldSiteUrl);
    	happyResp.setNewSiteUrl(newSiteUrl);
    
    	//WEBEX-9873 Error response to Atlas has wrong upstreamTrackingID
    	ProvResponseData responseToAtlas = SiteRenameUtil.constructResponseData(happyResp, serviceItems, site, trackingID);
    	String responseJson = new GsonBuilder().disableHtmlEscaping().create().toJson(responseToAtlas);
    	
		try {
			if (isImmediate)
				SiteRenameUtil.expireOldSiteUrlNow(newSiteUrl);  // Clean up old site rename records
		} catch (ProvisioningException e1) {
			logger.error("Exception error [{}] when cleaning up old siteUrl records", e1.getProvisioningError().getDescription());
	    	addProvisionerExceptionToRenameResults(happyResp, e1);
		}
    
    	try {
			SiteRenameUtil.sendSiteRenameResponseToAtlas(atlasCallBackURL, responseJson);
		} catch (ProvisioningException e2) {
			addProvisionerExceptionToRenameResults(happyResp, e2);
			ProvResponseData provResponseData = SiteRenameUtil.constructResponseData(happyResp, serviceItems, site, trackingID);
			String responseData = new Gson().toJson(provResponseData);
		    logger.error("Exception error [{}] when sending Response to Atlas {} ", e2.getProvisioningError().getDescription(),responseData);
		}   
    }
    
    /*
     * When MBS/admin site rename API returns not 200 or 202, 
     * any 400x to 500x,construct a error response to Atlas
     */
    private void constructAtlasErrorResponse(Site site, String atlasCallBackURL, String newSiteUrl, String oldSiteUrl, String trackingID, 
    												String serviceItems, ProvisioningException provException) {
    	
    	// Construct the response body
    	RenameResultsRequestBean happyResp = new RenameResultsRequestBean();
    	happyResp.setOldSiteUrl(oldSiteUrl);
    	happyResp.setNewSiteUrl(newSiteUrl);
    
    	addProvisionerExceptionToRenameResults(happyResp,provException);
    	
    	ProvResponseData responseToAtlas = SiteRenameUtil.constructResponseData(happyResp, serviceItems, site, trackingID);
    	String responseJson = new GsonBuilder().disableHtmlEscaping().create().toJson(responseToAtlas);
    	try {
			SiteRenameUtil.sendSiteRenameResponseToAtlas(atlasCallBackURL, responseJson);
		} catch (ProvisioningException e) {
			addProvisionerExceptionToRenameResults(happyResp, e);
			ProvResponseData provResponseData = SiteRenameUtil.constructResponseData(happyResp, serviceItems, site, trackingID);
			String responseData = new Gson().toJson(provResponseData);
		    logger.error("Exception error [{}] when sending Response to Atlas {} ", e.getProvisioningError().getDescription(),responseData);
		}    	
    }
    
    private void addProvisionerExceptionToRenameResults(RenameResultsRequestBean renameResults, ProvisioningException provException) {
    	
    	RenameResultsErrorDetails errorDetails = null;
    	List<RenameResultsErrorDetails> errorDetailsList = new ArrayList<RenameResultsErrorDetails>();
    	if( null != renameResults.getErrors()){
    		errorDetailsList = renameResults.getErrors();
		}
		if(null != provException && null != provException.getProvisioningError()){
			errorDetails  = new RenameResultsErrorDetails(provException.getProvisioningError().getCode().toString(), provException.getProvisioningError().getDescription());	
			errorDetailsList.add(errorDetails);
			renameResults.setErrors(errorDetailsList);
		}
    }
    
    /*
     * Construct error message for Atlas call back
     * only handle exceptions directly from site rename API.
     */
    private RenameResultsErrorDetails constructErrorMessage(String jsonString, int statusCode)
    {
    	RenameResultsErrorDetails errDetail = new RenameResultsErrorDetails();
    	JSONObject jobj = JSONObject.fromObject(jsonString);
    	String errorMsg = "Status code: " + statusCode + "  Message: ";
    	if (jobj.containsKey("errorMsg")) {
    		errorMsg = errorMsg + (String) jobj.get("errorMsg");
    		errDetail.setErrorCode("439098");
        	errDetail.setDescription(errorMsg);
    	}
    	else if (jobj.containsKey("errors")) {
    		String errCode = jobj.getJSONObject("errors").getString("errorCode");
    		errorMsg = errorMsg + "Internal server error";
    		errDetail.setErrorCode(errCode);
        	errDetail.setDescription(errorMsg);
    	}
    	return errDetail;
	}
}
