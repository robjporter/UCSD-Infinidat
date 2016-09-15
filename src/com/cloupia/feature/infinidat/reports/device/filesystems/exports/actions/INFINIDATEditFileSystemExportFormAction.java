package com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions;

import java.util.ArrayList;
import java.util.List;
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
import com.roporter.feature.json.myJson;

public class INFINIDATEditFileSystemExportFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATEditFileSystemExportFormAction.class);
	private static final String formId = "infinidat.filesystem.export.edit.report.action.form.EditFileSystem";
	private static final String ACTION_ID = "infinidat.filesystem.export.edit.report.form.action.EditFileSystem";
	private static final String ACTION_LABEL = "Edit Filesystem Export";
	private static final String ACTION_DESCRIPTION = "Edit Filesystem Export";

	private String exportName = "Export Name";
	private int exportAnonUID = 0;
	private int exportAnonGID = 0;
	private boolean exportSquash = false;
	private boolean exportPorts = false;
	private int exportMaxRead = 0;
	private int exportMaxWrite = 0;
	private int exportPrefRead = 0;
	private int exportPrefWrite = 0;
	private int exportReadDir = 0;
	
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
		page.bind( formId, INFINIDATEditFileSystemExportForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		this.exportName = "Export Name";
		this.exportAnonUID = 0;
		this.exportAnonGID = 0;
		this.exportSquash = false;
		this.exportPorts = false;
		this.exportMaxRead = 0;
		this.exportMaxWrite = 0;
		this.exportPrefRead = 0;
		this.exportPrefWrite = 0;
		this.exportReadDir = 0;
		
		String values = func.getExportInfoByID(id);
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- VALUES: " + values );}
		
		String value[] = values.split("\\|");
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- VALUE LENGTH : " + value.length );}
		
		if(value.length == 10) {
			this.exportName = value[0].trim();
			this.exportAnonUID = myFormat.safeStringToInt(value[1].trim());
			this.exportAnonGID = myFormat.safeStringToInt(value[2].trim());
			this.exportSquash = myFormat.safeStringToBoolean(value[3].trim());
			this.exportPorts = myFormat.safeStringToBoolean(value[4].trim());
			this.exportMaxRead = myFormat.safeStringToInt(value[5].trim());
			this.exportMaxWrite = myFormat.safeStringToInt(value[6].trim());
			this.exportPrefRead = myFormat.safeStringToInt(value[7].trim());
			this.exportPrefWrite = myFormat.safeStringToInt(value[8].trim());
			this.exportReadDir = myFormat.safeStringToInt(value[9].trim());
			
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- NAME: " + this.exportName );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- UID: " + this.exportAnonUID );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- GID: " + this.exportAnonGID );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- SQUASH: " + this.exportSquash );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- PORTS: " + this.exportPorts );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- MAX READ: " + this.exportMaxRead );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- MAX WRITE: " + this.exportMaxWrite );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- PREF READ: " + this.exportPrefRead );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- PREF WRITE: " + this.exportPrefWrite );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:loadDataToPage ####---- PREF DIR: " + this.exportReadDir );}
		}
		
		INFINIDATEditFileSystemExportForm form = new INFINIDATEditFileSystemExportForm();
		form.setName(this.exportName);
		form.setAnonymousUID(this.exportAnonUID);
		form.setAnonymousGID(this.exportAnonGID);
		form.setSquash(this.exportSquash);
		form.setPorts(this.exportPorts);
		form.setMaxRead(this.exportMaxRead);
		form.setMaxWrite(this.exportMaxWrite);
		form.setPrefReadSize(this.exportPrefRead);
		form.setPrefWriteSize(this.exportPrefWrite);
		form.setPrefReadDir(this.exportReadDir);
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_STATIC_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATEditFileSystemExportForm form = (INFINIDATEditFileSystemExportForm) obj;
		String name = form.getName().trim();
		int uid = form.getAnonymousUID();
		int gid = form.getAnonymousGID();
		boolean squash = form.getSquash();
		boolean ports = form.getPorts();
		int mread = form.getMaxRead();
		int mwrite = form.getMaxWrite();
		int pread = form.getPrefReadSize();
		int pwrite = form.getPrefWriteSize();
		int pdir = form.getPrefReadDir();
		
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- UID: " + uid + " | VOLUMEPOOL: " + this.exportAnonUID );}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- GID: " + gid + " | VOLUMETYPE: " + this.exportAnonGID);}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- SQUASH: " + squash + " | VOLUMESIZE: " + this.exportSquash);}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- PORTS: " + ports + " | VOLUMESSD: " + this.exportPorts);}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- MREAD: " + mread + " | VOLUMEPOOL: " + this.exportMaxRead );}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- MWRITE: " + mwrite + " | VOLUMETYPE: " + this.exportMaxWrite);}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- PREAD: " + pread + " | VOLUMESIZE: " + this.exportPrefRead);}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- PWRITE: " + pwrite + " | VOLUMESSD: " + this.exportPrefWrite);}
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- PDIR: " + pdir + " | VOLUMESSD: " + this.exportReadDir);}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		List<String> toSend = new ArrayList<String>(); 
		
		if(this.exportAnonUID != uid) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING UID" );}
			toSend.add("\"anonymous_uid\": " + uid);
		}
		if(this.exportAnonGID != gid) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING GID" );}
			toSend.add("\"anonymous_gid\": " + gid);
		}
		if(this.exportSquash != squash) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING SQUASH" );}
			toSend.add("\"make_all_users_anonymous\": " + squash);
		}
		if(this.exportPorts != ports) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING PORTS" );}
			toSend.add("\"privileged_port\": " + ports);
		}
		if(this.exportMaxRead != mread) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING MAX READ" );}
			toSend.add("\"max_read\": " + mread);
		}
		if(this.exportMaxWrite != mwrite) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING MAX WRITE" );}
			toSend.add("\"max_write\": " + mwrite);
		}
		if(this.exportPrefRead != pread) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING PREF READ" );}
			toSend.add("\"pref_read\": " + pread);
		}
		if(this.exportPrefWrite != pwrite) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING PREF WRITE" );}
			toSend.add("\"pref_write\": " + pwrite);
		}
		if(this.exportReadDir != pdir) {
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- CHANGING READ DIR" );}
			toSend.add("\"pref_readdir\": " + pdir);
		}
		
		String send = myJson.formatJSON(toSend);
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- TO SEND: " + send );}
		
		String result = func.editExportByID(id,send).trim();
		if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(INFINIDATConstants.DEBUG_EXPORTS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- RESULT STATUS: " + results[0]);}
				if(results[INFINIDATConstants.POS_EXPORT_STATUS].split(":")[1].trim().equals("SUCCESS")) {
					page.setPageMessage("FileSystem Export with path: " + name + " has been updated Successfully.");
					func.refreshExports();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- RESULT LENGTH != 1" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "FileSystem Export edit failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATEditFileSystemExportFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;	
	}
}
