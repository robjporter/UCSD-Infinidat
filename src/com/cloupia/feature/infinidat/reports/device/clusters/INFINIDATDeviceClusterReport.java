package com.cloupia.feature.infinidat.reports.device.clusters;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.clusters.actions.INFINIDATAddHostToClusterFormAction;
import com.cloupia.feature.infinidat.reports.device.clusters.actions.INFINIDATAddLunToClusterFormAction;
import com.cloupia.feature.infinidat.reports.device.clusters.actions.INFINIDATAddNewClusterFormAction;
import com.cloupia.feature.infinidat.reports.device.clusters.actions.INFINIDATDeleteClusterFormAction;
import com.cloupia.feature.infinidat.reports.device.clusters.actions.INFINIDATDeleteHostToClusterFormAction;
import com.cloupia.feature.infinidat.reports.device.clusters.actions.INFINIDATDeleteLunToClusterFormAction;
import com.cloupia.feature.infinidat.reports.device.clusters.actions.INFINIDATEditClusterFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaEasyReportWithActions;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;

public class INFINIDATDeviceClusterReport extends CloupiaEasyReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceClusterReport.class );
	private static final String NAME = "infinidat.device.cluster.report";
	private static final String LABEL = "Clusters";

	public INFINIDATDeviceClusterReport() {
		super( NAME, LABEL, INFINIDATDeviceClusterReport.class );
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:INFINIDATDeviceClusterReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:CloupiaReportAction ####----" );}
		INFINIDATAddNewClusterFormAction newClusterAction = new INFINIDATAddNewClusterFormAction();
		INFINIDATEditClusterFormAction editClusterAction = new INFINIDATEditClusterFormAction();
		INFINIDATDeleteClusterFormAction deleteClusterAction = new INFINIDATDeleteClusterFormAction();
		INFINIDATAddHostToClusterFormAction newHostToClusterAction = new INFINIDATAddHostToClusterFormAction();
		INFINIDATDeleteHostToClusterFormAction deleteHostToClusterAction = new INFINIDATDeleteHostToClusterFormAction();
		INFINIDATAddLunToClusterFormAction newLunToClusterAction = new INFINIDATAddLunToClusterFormAction();
		INFINIDATDeleteLunToClusterFormAction deleteLunToClusterAction = new INFINIDATDeleteLunToClusterFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 7 ];
		actions[ 0 ] = newClusterAction;
		actions[ 1 ] = editClusterAction;
		actions[ 2 ] = deleteClusterAction;
		actions[ 3 ] = newHostToClusterAction;
		actions[ 4 ] = deleteHostToClusterAction;
		actions[ 5 ] = newLunToClusterAction;
		actions[ 6 ] = deleteLunToClusterAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:getImplementationClass ####----" );}
		return INFINIDATDeviceClusterReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
