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

public class INFINIDATEditVolumeFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATEditVolumeFormAction.class);
	private static final String formId = "infinidat.volume.edit.report.action.form.EditVolume";
	private static final String ACTION_ID = "infinidat.volume.edit.report.form.action.EditVolume";
	private static final String ACTION_LABEL = "Edit Volume";
	private static final String ACTION_DESCRIPTION = "Edit Volume";
	
	private String volumeName = "";
	private int volumePool = 0;
	private String volumeType = "Thin";
	private int volumeSize = 0;
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
		page.bind(formId, INFINIDATEditVolumeForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}	

		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		this.volumeName = "Volume Name";
		this.volumePool = 0;
		this.volumeType = "Thin";
		this.volumeSize = 10;
		this.volumeSSD = true;
		
		String values = func.getVolumeInfoFromID(id);
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- VALUES: " + values );}
			
		String value[] = values.split("\\|");
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- VALUE LENGTH : " + value.length );}
		
		if(value.length == 5) {
			this.volumeName = value[0].trim();
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- NAME: " + this.volumeName );}
			this.volumePool = myFormat.safeStringToInt(value[1].trim());
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- POOL: " + this.volumePool );}
			this.volumeType = value[2].trim();
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- TYPE: " + this.volumeType );}
			long tmp = myFormat.safeStringToLong(value[3].trim());
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- TMP: " + tmp );}
			this.volumeSize = myFormat.humanReadableIntFromLong(tmp, true);
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- VSIZE: " + this.volumeSize );}
			this.volumeSSD = myFormat.safeStringToBoolean(value[4].trim());
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:loadDataToPage ####---- SSD: " + this.volumeSSD );}
		}
		
		INFINIDATEditVolumeForm form = new INFINIDATEditVolumeForm();
		form.setName(this.volumeName);
		form.setPoolID(this.volumePool);
		form.setProvType(this.volumeType);
		form.setSize(this.volumeSize);
		form.setSSD(this.volumeSSD);
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}

	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}	
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATEditVolumeForm form = (INFINIDATEditVolumeForm) obj;
		String name = form.getName().trim();
		int pool = form.getPoolID();
		String type = form.getProvType().trim().toUpperCase();
		long size = form.getSize();
		boolean ssd = form.getSSD();
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- NAME: " + name + " | VOLUMENAME: " + this.volumeName );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- POOL: " + pool + " | VOLUMEPOOL: " + this.volumePool );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- TYPE: " + type + " | VOLUMETYPE: " + this.volumeType);}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- SIZE: " + size + " | VOLUMESIZE: " + this.volumeSize);}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- SSD: " + ssd + " | VOLUMESSD: " + this.volumeSSD);}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);

		String result1 = "";
		String result2 = "";
		String result3 = "";
		String result4 = "";
		String result5 = "";
		
		if(!this.volumeName.equals(name)) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- CHANGING NAME" );}
			result1 = func.editVolumeByID(id, "name", name);
		}
		if(this.volumePool != pool) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- CHANGING POOL" );}
			result2 = func.moveVolumeByID(id, pool);
		}
		if(!this.volumeType.equalsIgnoreCase(type)) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- CHANGING TYPE" );}
			result3 = func.editVolumeByID(id, "provtype", type);
		}
		if(this.volumeSize != size) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- CHANGING SIZE" );}
			result4 = func.editVolumeByID(id, "size", myFormat.longFromHumanReadable("GB", size, true));
		}
		if(this.volumeSSD != ssd) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- CHANGING SSD" );}
			result5 = func.editVolumeByID(id, "ssd_enabled", ssd);
		}

		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT1: " + result1 );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT2: " + result2 );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT3: " + result3 );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT4: " + result4 );}
		if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT5: " + result5 );}
		
		int complete = 0;
		
		if(result1 != null && !result1.isEmpty()) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT 1 != NULL" );}
			String results[] = result1.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 1");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 1 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 1 == NULL" );}
		}
		
		if(result2 != null && !result2.isEmpty()) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT 2 != NULL" );}
			String results[] = result2.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 2");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 2 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 2 == NULL" );}
		}
		
		if(result3 != null && !result3.isEmpty()) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT 3 != NULL" );}
			String results[] = result3.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 3");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 3 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 3 == NULL" );}
		}
		
		if(result4 != null && !result4.isEmpty()) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT 4 != NULL" );}
			String results[] = result4.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 4");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 4 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 4 == NULL" );}
		}

		if(result5 != null && !result5.isEmpty()) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- RESULT 5 != NULL" );}
			String results[] = result5.split("\\|");
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String code = results[results.length-1].split(":")[1].trim();
					if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- CODE: " + code );}
					complete += 1;
				} else {
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- THERE WAS AN ERROR - RESULT 5");}
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 5 LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemFormAction:validatePageData ####---- RESULT 5 == NULL" );}
		}
		
		if(complete > 0) {
			if(INFINIDATConstants.DEBUG_VOLUMES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- WE CHANGED SOMETHING ON THE VOLUME" );}
			page.setPageMessage("Volume has been updated Successfully.");
			func.refreshVolumes();
			page.setRefreshInSeconds(10);
			return PageIf.STATUS_OK;
		} else {
			page.setPageMessage( "Volume update failed!" );
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditVolumeFormAction:validatePageData ####---- ERROR!!!!" );}
			return PageIf.STATUS_ERROR;
		}
	}

}
