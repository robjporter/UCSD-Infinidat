package com.cloupia.feature.infinidat.reports.device.logs;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceEventLogReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceEventLogReport.class );
	private static final String NAME = "infinidat.device.log.event.report";
	private static final String LABEL = "Event Log";

	public INFINIDATDeviceEventLogReport() {
		super();
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:INFINIDATDeviceHostReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_LOGS) {
			if (INFINIDATConstants.DEBUG) {logger.info( "----#### INFINIDATDeviceEventLogReport:getActions ####----" );}
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:getImplementationClass ####----" );}
		return INFINIDATDeviceEventLogReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}