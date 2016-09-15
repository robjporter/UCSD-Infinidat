package com.cloupia.feature.infinidat.reports.device.filesystems.snapshots.actions;

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

@SuppressWarnings("unused")
public class INFINIDATCloneFileSystemSnapshotFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATCloneFileSystemSnapshotFormAction.class);
	private static final String formId = "infinidat.filesystem.clone.snapshot.report.action.form.CloneFileSystemSnapshot";
	private static final String ACTION_ID = "infinidat.filesystem.clone.snapshot.report.form.action.CloneFileSystemSnapshot";
	private static final String ACTION_LABEL = "Clone Snapshot";
	private static final String ACTION_DESCRIPTION = "Clone FileSystem Snapshot";
	
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
		page.bind(formId,INFINIDATCloneFileSystemSnapshotForm.class);
	}

	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}	
		
		INFINIDATCloneFileSystemSnapshotForm form = new INFINIDATCloneFileSystemSnapshotForm();
		form.setName("Clone-");
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}

	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneVolumeSnapshotFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATCloneFileSystemSnapshotForm form = (INFINIDATCloneFileSystemSnapshotForm) obj;
		String name = "Clone-" + form.getName();
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####----  NAME: " + name);}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.cloneFileSystemSnapshot(id,name);
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- RESULT: " + result );}
		
		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					page.setPageMessage("FileSystem Snapshot has been cloned successfully to " + name );
					func.refreshClones();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "FileSystem Snapshot clone failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATCloneFileSystemSnapshotFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
