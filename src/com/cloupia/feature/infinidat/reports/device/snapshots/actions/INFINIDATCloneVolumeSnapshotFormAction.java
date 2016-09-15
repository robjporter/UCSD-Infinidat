package com.cloupia.feature.infinidat.reports.device.snapshots.actions;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.feature.infinidat.reports.device.volume.clones.INFINIDATDeviceVolumeClonesReport;
import com.cloupia.model.cIM.ConfigTableAction;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.forms.wizard.Page;
import com.cloupia.service.cIM.inframgr.forms.wizard.PageIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaPageAction;

public class INFINIDATCloneVolumeSnapshotFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATCloneVolumeSnapshotFormAction.class);
	private static final String formId = "infinidat.volume.clone.snapshot.report.action.form.CloneVolumeSnapshot";
	private static final String ACTION_ID = "infinidat.volume.clone.snapshot.report.form.action.CloneVolumeSnapshot";
	private static final String ACTION_LABEL = "Clone Snapshot";
	private static final String ACTION_DESCRIPTION = "Clone Volume Snapshot";
	
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
		page.bind(formId,INFINIDATCloneVolumeSnapshotForm.class);
	}

	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}	
		
		INFINIDATCloneVolumeSnapshotForm form = new INFINIDATCloneVolumeSnapshotForm();
		form.setName("Clone-");
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}

	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATCloneVolumeSnapshotForm form = (INFINIDATCloneVolumeSnapshotForm) obj;
		String name = "Clone-" + form.getName();
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####----  NAME: " + name);}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.cloneVolumeSnapshot(id,name);
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					page.setPageMessage("Volume Snapshot has been cloned successfully to " + name );
					func.refreshClones();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Volume Snapshot clone failed!" );
		logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:validatePageData ####---- ERROR!!!!" );
		return PageIf.STATUS_ERROR;
	}
}
