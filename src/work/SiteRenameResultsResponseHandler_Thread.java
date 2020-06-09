package com.cisco.webex.provisioning.site;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.webex.pml.ProvisioningException;
import com.cisco.webex.pml.output.provisioning.ProvResponseData;
import com.cisco.webex.pml.util.provisioning.ProvisioningErrors.ProvisioningError;
import com.cisco.webex.provisioning.util.SiteRenameUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SiteRenameResultsResponseHandler implements Runnable{

	public static final Logger logger = LoggerFactory.getLogger(SiteRenameResultsResponseHandler.class);

	private RenameResultsRequestBean renameResult = null;
	private String serviceItems = "";
	private String orderId = "";
	private String immediate = "";
	private String expiration = "";
	
	public SiteRenameResultsResponseHandler(RenameResultsRequestBean renameResult, String serviceItems, String orderId, String expiration, String immediate ){
		this.serviceItems = serviceItems;
		this.renameResult = renameResult;
		this.orderId = orderId;
		this.expiration = expiration;
		this.immediate = immediate;
	}
	
	@Override
	public void run() {
		logger.info("Start processing site rename response from MBS");
		String responseJson = null;
		ProvResponseData responseToAtlas = null;
		try{
			// if we get the request to expire old site url immediately, call WAPI to cleanup old site url from DB.
			if(null != expiration && null != immediate)
				SiteRenameUtil.expireOldSiteUrlNow(renameResult.getNewSiteUrl());
		}catch(ProvisioningException e){
			addProvisionerErrorToRenameResult(e); // add error to the rename result errors list so that it'll be reported to Atlas
		}
		responseToAtlas = SiteRenameUtil.constructResponseData(renameResult, serviceItems, null, null);

		// convert ProvResponseData object to json string
		if(null != responseToAtlas)
			responseJson = new GsonBuilder().disableHtmlEscaping().create().toJson(responseToAtlas);
		// send response to Atlas
		try {
			SiteRenameUtil.sendSiteRenameResponseToAtlas(SiteRenameUtil.getAtlasCallbackUrl(orderId), responseJson );
		} catch (ProvisioningException e1) {
			addProvisionerErrorToRenameResult(e1);
			ProvResponseData provResponseData = SiteRenameUtil.constructResponseData(renameResult, serviceItems, null, responseToAtlas.getTrackingId());
			String responseData = new Gson().toJson(provResponseData);
			logger.error("Exception error [{}] when sending Response to Atlas [{}] ", e1.getProvisioningError().getDescription(),responseData);
		}
		logger.info("Done processing site rename response from MBS");
	}

	private void addProvisionerErrorToRenameResult(ProvisioningException e) {
		List<RenameResultsErrorDetails> renameErrors = null;
		if( null != renameResult.getErrors()){
			renameErrors = renameResult.getErrors();
		}else
			renameErrors = new ArrayList<> ();
		if(null != e && null != e.getProvisioningError()){
			ProvisioningError provError = e.getProvisioningError();
			RenameResultsErrorDetails err = new RenameResultsErrorDetails(provError.getCode().toString(), provError.getDescription() );
			renameErrors.add(err);
		}
		if( null != renameErrors)
			renameResult.setErrors(renameErrors);
	}



}
