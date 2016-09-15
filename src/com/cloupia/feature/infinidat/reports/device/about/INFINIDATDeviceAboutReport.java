package com.cloupia.feature.infinidat.reports.device.about;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

@SuppressWarnings({"rawtypes","unused"})
public class INFINIDATDeviceAboutReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceAboutReport.class );
	private static final String NAME = "com.cloupia.feature.infinidat.reports.device.about";
	private static final String LABEL = "About";

	public INFINIDATDeviceAboutReport() {
		super();
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:INFINIDATDeviceAboutReport ####----" );}
	}
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReport:getImplementationClass ####----");}
		return INFINIDATDeviceAboutReportAction.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:isEasyReport ####----" );}
		return true;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:isLeafReport ####----" );}
		return true;
	}
	@Override
	public int getReportType() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:getReportType ####----" );}
		return ReportDefinition.REPORT_TYPE_CONFIG_FORM;
	}
	@Override
	public boolean isManagementReport() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:isManagementReport ####----" );}
		return true;
	}
	@Override
	public boolean showInSummary() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:showInSummary ####----" );}
	    return true;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceAboutReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}