package com.cloupia.feature.infinidat.reports.device.enetwork;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceEInterfaceReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceEInterfaceReport.class );
	private static final String NAME = "infinidat.device.ethernet.interface.report";
	private static final String LABEL = "Ethernet";

	public INFINIDATDeviceEInterfaceReport() {
		super();
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:INFINIDATDeviceLunReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:getActions ####----" );
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:getImplementationClass ####----" );
		return INFINIDATDeviceEInterfaceReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		logger.info( "----#### INFINIDATDeviceEInterfaceReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
