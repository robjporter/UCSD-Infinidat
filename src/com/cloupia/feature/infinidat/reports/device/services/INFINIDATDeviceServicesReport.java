package com.cloupia.feature.infinidat.reports.device.services;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceServicesReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceServicesReport.class );
	private static final String NAME = "infinidat.device.service.report";
	private static final String LABEL = "Services";

	public INFINIDATDeviceServicesReport() {
		super();
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:INFINIDATDeviceServicesReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:getActions ####----" );}
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:getImplementationClass ####----" );}
		return INFINIDATDeviceServicesReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceServicesReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
