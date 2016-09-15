package com.cloupia.feature.infinidat.reports.device.volume.actions;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;
import com.roporter.feature.format.myFormat;

public class INFINIDATAddNewVolumeFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATAddNewVolumeFormAction.class);
	private static final String formId = "infinidat.volume.new.report.action.form.AddVolume";
	private static final String ACTION_ID = "infinidat.volume.new.report.form.action.AddVolume";
	private static final String ACTION_LABEL = "Create";
	private static final String ACTION_DESCRIPTION = "Create new Volume";
	
	@Override
	public String getActionId() {
		return ACTION_ID;
	}
	@Override
	public String getFormId() {
		return formId;
	}
	@Override
	public String getLabel() {
		return ACTION_LABEL;
	}
	@Override
	public String getTitle() {
		return ACTION_LABEL;
	}
	@Override
	public String getDescription() {
		return ACTION_DESCRIPTION;
	}
	@Override
	public int getActionType() {
		return ConfigTableAction.ACTION_TYPE_POPUP_FORM;
	}
	@Override
	public boolean isSelectionRequired() {
		return false;
	}
	@Override
	public boolean isDoubleClickAction() {
		return false;
	}
	@Override
	public boolean isDrilldownAction() {
		return false;
	}
	@Override
	public void definePage(Page page, ReportContext context) {
		page.bind( formId, INFINIDATAddNewVolumeForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATAddNewVolumeForm form = new INFINIDATAddNewVolumeForm();
		form.setName("Volume Name");
		form.setPoolID(0);
		form.setProvType(INFINIDATConstants.DEFAULT_PROVISION_TYPE);
		form.setSize(10);
		form.setSSD(true);
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_STATIC_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATAddNewVolumeForm form = (INFINIDATAddNewVolumeForm) obj;
		String name = form.getName();
		int pool = form.getPoolID();
		String type = form.getProvType();
		long size = form.getSize();
		boolean ssd = form.getSSD();
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- NAME: " + name );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- POOL: " + pool );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- TYPE: " + type );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- SIZE: " + size );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- SSD: " + ssd );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.createNewVolume(name, "GB", myFormat.safeLongToString(size), type, myFormat.safeIntToString(pool), ssd).trim();
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String vid = results[1].split(":")[1].trim();
					String vserial = results[4].split(":")[1].trim();
					page.setPageMessage("Volume has been created Successfully with the ID: " + vid + " and the serial number: " + vserial + ".");
					func.refreshVolumes();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- RESULT LENGTH != 5" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Volume creation failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewVolumeFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
