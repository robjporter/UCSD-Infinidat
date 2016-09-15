package com.cloupia.feature.infinidat.reports.device.ibnetwork;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
//import com.cloupia.feature.infinidat.forms.actions.host.INFINIDATAddNewHostFormAction;
//import com.cloupia.feature.infinidat.forms.actions.host.INFINIDATDeleteHostFormAction;
//import com.cloupia.feature.infinidat.forms.actions.host.INFINIDATEditHostFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceIBInterfaceReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceIBInterfaceReport.class );
	private static final String NAME = "infinidat.device.IB.network.report";
	private static final String LABEL = "Infiniband";

	public INFINIDATDeviceIBInterfaceReport() {
		super();
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:INFINIDATDeviceIBInterfaceReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:getActions ####----" );
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:getImplementationClass ####----" );
		return INFINIDATDeviceIBNetworkReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		logger.info( "----#### INFINIDATDeviceIBInterfaceReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}