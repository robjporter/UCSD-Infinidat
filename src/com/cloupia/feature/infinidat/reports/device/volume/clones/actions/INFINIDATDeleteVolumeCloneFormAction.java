package com.cloupia.feature.infinidat.reports.device.volume.clones.actions;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

@SuppressWarnings("unused")
public class INFINIDATDeleteVolumeCloneFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATDeleteVolumeCloneFormAction.class);
	private static final String formId = "infinidat.volume.delete.report.action.form.DeleteVolume";
	private static final String ACTION_ID = "infinidat.volume.delete.report.form.action.DeleteVolume";
	private static final String ACTION_LABEL = "Delete";
	private static final String ACTION_DESCRIPTION = "Delete Volume";
	
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
		return true;
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
		page.bind(formId,INFINIDATDeleteVolumeCloneForm.class);
	}

	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteVolumeFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteVolumeFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteVolumeFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}	
		
		INFINIDATDeleteVolumeCloneForm form = new INFINIDATDeleteVolumeCloneForm();
		form.setMessage("Delete the selected Volume?");
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}

	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.deleteVolume(id);
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String name = results[2].split(":")[1].trim();
					String serial = results[4].split(":")[1].trim();
					page.setPageMessage("Lun " + name + " with serial number: " + serial + " has been deleted successfully." );
					func.refreshVolumes();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Volume deletion failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATDeleteVolumeFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
