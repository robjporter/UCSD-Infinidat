package com.cloupia.feature.infinidat.reports.device.nodes;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATNodes;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaEasyReportWithActions;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;

public class INFINIDATDeviceNodesReport extends CloupiaEasyReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceNodesReport.class );
	private static final String NAME = "infinidat.device.nodes.report";
	private static final String LABEL = "Nodes";

	public INFINIDATDeviceNodesReport() {
		super( NAME, LABEL, INFINIDATNodes.class );
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:INFINIDATDeviceNodesReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:getActions ####----" );}
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:getImplementationClass ####----" );}
		return INFINIDATDeviceNodesReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceNodesReport:getMapRules ####----" );}
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( context.getId() );
		rule.setContextType( context.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
