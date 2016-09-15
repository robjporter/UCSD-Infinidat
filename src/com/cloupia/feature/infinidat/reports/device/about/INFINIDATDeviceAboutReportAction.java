package com.cloupia.feature.infinidat.reports.device.about;

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
public class INFINIDATDeviceAboutReportAction extends CloupiaPageAction {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceAboutReportAction.class );
	private static final String formId = "infinidat.device.about.report.form";
	private static final String ACTION_ID = "infinidat.device.about.report.action";
	private static final String LABEL = "label unused, report label overrides this when using a config form!";

	@Override
	public String getActionId() {
		return ACTION_ID;
	}
	public String getFormId() {
		return formId;
	}
	@Override
	public String getLabel() {
		return LABEL;
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
		page.bind( formId, INFINIDATDeviceAboutReportObject.class );
		page.setSubmitButton( "Save Changes" );
	}
	@Override
	public void loadDataToPage( Page page, ReportContext context, WizardSession session ) throws Exception {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportAction:loadDataToPage ####----");}
		INFINIDATDeviceAboutReportObject form = new INFINIDATDeviceAboutReportObject();
		form.setCiscoLogo(form.cisco_logo);
		form.setInfinidatLogo(form.infinidat_logo);
		form.setJointLogo(form.cisco_logo + form.infinidat_logo);
		String version = INFINIDATFunctions.httpSimpleGet2("https://raw.githubusercontent.com/roporter/UCSD-Infinidat/master/RELEASE");
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportAction:loadDataToPage ####---- VERSION: " + version);}
		form.setNewBuildVersion(version);
		session.getSessionAttributes().put(formId,form);
		page.marshallFromSession(formId);
	}
	@Override
	public int validatePageData( Page page, ReportContext context, WizardSession session ) throws Exception {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportAction:validatePageData ####----");}
		
		Object obj = page.unmarshallToSession(formId);
		INFINIDATDeviceAboutReportObject form = (INFINIDATDeviceAboutReportObject) obj;
		
		int timeout = form.getHTTPTimeout();
		String url = form.getHTTPProxyURL();
		int port = form.getHTTPProxyPort();
		
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportAction:validatePageData ####---- TIMEOUT: " + timeout);}
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportAction:validatePageData ####---- URL: " + url);}
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportAction:validatePageData ####---- PORT: " + port);}
		
		return PageIf.STATUS_OK;
	}
	@Override
	public String getTitle() {
		return LABEL;
	}
}