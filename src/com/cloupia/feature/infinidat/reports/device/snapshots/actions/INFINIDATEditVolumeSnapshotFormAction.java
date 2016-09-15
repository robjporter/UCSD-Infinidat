package com.cloupia.feature.infinidat.reports.device.snapshots.actions;

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

@SuppressWarnings("unused")
public class INFINIDATEditVolumeSnapshotFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATEditVolumeSnapshotFormAction.class);
	private static final String formId = "infinidat.volume.edit.snapshot.report.action.form.EditVolumeSnapshot";
	private static final String ACTION_ID = "infinidat.volume.edit.snapshot.report.form.action.EditVolumeSnapshot";
	private static final String ACTION_LABEL = "Edit Snapshot";
	private static final String ACTION_DESCRIPTION = "Edit Snapshot";
	
	private String volumeName = "";
	private boolean volumeSSD = true;
	
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
		page.bind(formId, INFINIDATEditVolumeSnapshotForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}	

		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		this.volumeName = "Volume Name";
		this.volumeSSD = true;
		
		String values = func.getVolumeInfoFromID(id);
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:loadDataToPage ####---- VALUES: " + values );}
			
		String value[] = values.split("\\|");
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:loadDataToPage ####---- VALUE LENGTH : " + value.length );}
		
		if(value.length == 5) {
			this.volumeName = value[INFINIDATConstants.POS_EDIT_VOLUME_NAME].trim();
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:loadDataToPage ####---- NAME: " + this.volumeName );}
			this.volumeSSD = myFormat.safeStringToBoolean(value[INFINIDATConstants.POS_EDIT_VOLUME_SSD].trim());
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:loadDataToPage ####---- SSD: " + this.volumeSSD );}
		}
		
		INFINIDATEditVolumeSnapshotForm form = new INFINIDATEditVolumeSnapshotForm();
		form.setName(this.volumeName);
		form.setSSD(this.volumeSSD);
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}

	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}	
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATEditVolumeSnapshotForm form = (INFINIDATEditVolumeSnapshotForm) obj;
		String name = form.getName().trim();
		boolean ssd = form.getSSD();
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- NAME: " + name + " | VOLUMENAME: " + this.volumeName );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- SSD: " + ssd + " | VOLUMESSD: " + this.volumeSSD);}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);

		String result1 = "";
		String result2 = "";
		
		if(!this.volumeName.equals(name)) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- CHANGING NAME" );}
			result1 = func.editVolumeByID(id, "name", name);
		}
		if(this.volumeSSD != ssd) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- CHANGING SSD" );}
			result2 = func.editVolumeByID(id, "ssd_enabled", ssd);
		}

		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT1: " + result1 );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT2: " + result2 );}
		
		int complete = 0;
		
		if(result1 != null && !result1.isEmpty()) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT 1 != NULL" );}
			String results[] = result1.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 1");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT 1 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT 1 == NULL" );}
		}
		
		if(result2 != null && !result2.isEmpty()) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT 2 != NULL" );}
			String results[] = result2.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 2");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT 2 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- RESULT 2 == NULL" );}
		}
		
		if(complete > 0) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- WE CHANGED SOMETHING ON THE VOLUME" );}
			page.setPageMessage("Volume has been updated Successfully.");
			func.refreshVolumes();
			page.setRefreshInSeconds(10);
			return PageIf.STATUS_OK;
		} else {
			page.setPageMessage( "Volume update failed!" );
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeSnapshotFormAction:validatePageData ####---- ERROR!!!!" );}
			return PageIf.STATUS_ERROR;
		}
	}

}
