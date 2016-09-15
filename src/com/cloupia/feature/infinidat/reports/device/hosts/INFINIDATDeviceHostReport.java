package com.cloupia.feature.infinidat.reports.device.hosts;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATAddVolumeToHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATAddCloneToHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATAddNewHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATAddNewPortToHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATAddSnapshotToHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATDeleteHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATDeleteVolumeToHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATDeletePortToHostFormAction;
import com.cloupia.feature.infinidat.reports.device.hosts.actions.INFINIDATEditHostFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceHostReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceHostReport.class );
	private static final String NAME = "infinidat.device.host.report";
	private static final String LABEL = "Hosts";

	public INFINIDATDeviceHostReport() {
		super();
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:INFINIDATDeviceHostReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:getActions ####----" );}
		INFINIDATAddNewHostFormAction newHostAction = new INFINIDATAddNewHostFormAction();
		INFINIDATEditHostFormAction editHostAction = new INFINIDATEditHostFormAction();
		INFINIDATDeleteHostFormAction deleteHostAction = new INFINIDATDeleteHostFormAction();
		INFINIDATAddNewPortToHostFormAction addHostPortAction = new INFINIDATAddNewPortToHostFormAction();
		INFINIDATDeletePortToHostFormAction deleteHostPortAction = new INFINIDATDeletePortToHostFormAction();
		INFINIDATAddVolumeToHostFormAction mapHostAction = new INFINIDATAddVolumeToHostFormAction();
		INFINIDATAddSnapshotToHostFormAction mapSnapAction = new INFINIDATAddSnapshotToHostFormAction();
		INFINIDATAddCloneToHostFormAction mapCloneAction = new INFINIDATAddCloneToHostFormAction();
		INFINIDATDeleteVolumeToHostFormAction unmapHostAction = new INFINIDATDeleteVolumeToHostFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 9 ];
		actions[ 0 ] = newHostAction;
		actions[ 1 ] = editHostAction;
		actions[ 2 ] = deleteHostAction;
		actions[ 3 ] = addHostPortAction;
		actions[ 4 ] = deleteHostPortAction;
		actions[ 5 ] = mapHostAction;
		actions[ 6 ] = mapSnapAction;
		actions[ 7 ] = mapCloneAction;
		actions[ 8 ] = unmapHostAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:getImplementationClass ####----" );}
		return INFINIDATDeviceHostReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReport:getMapRules ####----" );}
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( context.getId() );
		rule.setContextType( context.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}