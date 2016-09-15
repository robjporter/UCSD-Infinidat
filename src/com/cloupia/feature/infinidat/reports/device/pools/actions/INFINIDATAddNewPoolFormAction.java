package com.cloupia.feature.infinidat.reports.device.pools.actions;

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

public class INFINIDATAddNewPoolFormAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger(INFINIDATAddNewPoolFormAction.class);
	private static final String formId = "infinidat.pool.new.report.action.form.AddPool";
	private static final String ACTION_ID = "infinidat.pool.new.report.form.action.AddPool";
	private static final String ACTION_LABEL = "Create";
	private static final String ACTION_DESCRIPTION = "Create new Pool";
	

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
		page.bind( formId, INFINIDATAddNewPoolForm.class);
	}
	@Override
	public void loadDataToPage(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewPoolFormAction:loadDataToPage ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewPoolFormAction:loadDataToPag ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewPoolFormAction:loadDataToPag ####---- ACCNAME: " + accName );}
		}
		
		INFINIDATAddNewPoolForm form = new INFINIDATAddNewPoolForm();
		form.setName("Pool Name");
		form.setLinkStatus(true);
		form.setPhysicalSize(1000);
		form.setVirtualSize(1000);
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData(Page page, ReportContext context, WizardSession session) throws Exception {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####----" );}
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_STATIC_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- ID: " + id );}
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- ACCNAME: " + accName );}
		}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATAddNewPoolForm form = (INFINIDATAddNewPoolForm) obj;
		String name = form.getName();
		Boolean link = form.getLinkStatus();
		int pSize = form.getPhysicalSize();
		int vSize = form.getVirtualSize();
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- NAME: " + name );}
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- LINK: " + link );}
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- PSIZE: " + pSize );}
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- VSIZE: " + vSize );}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String result = func.createNewPool(name, "GB", link, myFormat.safeIntToString(pSize), myFormat.safeIntToString(vSize), false).trim();
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- RESULT: " + result );}

		if(result != null) {
			String results[] = result.split("\\|");
			if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- RESULTS LENGTH: " + results.length);}
			if(results.length > 0) {
				if(results[0].split(":")[1].trim().equals("SUCCESS")) {
					String vid = results[1].split(":")[1].trim();
					String state = results[13].split(":")[1].trim();
					page.setPageMessage("Pool has been created Successfully with the ID: " + vid + " and set with State: " + state + ".");
					func.refreshPools();
					page.setRefreshInSeconds(10);
					return PageIf.STATUS_OK;
				} else {
					page.setPageMessage(results[2].split(":")[1].trim());
					if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- WE RECEIVED A NON SUCCESSFUL MESSAGE" );}
					return PageIf.STATUS_ERROR;	
				}
			} else {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- RESULT LENGTH != 5" );}
			}
		} else {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- RESULT == NULL" );}
		}
		page.setPageMessage( "Pool creation failed!" );
		if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATAddNewPoolFormAction:validatePageData ####---- ERROR!!!!" );}
		return PageIf.STATUS_ERROR;
	}
}
