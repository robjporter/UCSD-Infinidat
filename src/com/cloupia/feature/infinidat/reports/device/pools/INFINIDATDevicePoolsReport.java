package com.cloupia.feature.infinidat.reports.device.pools;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.pools.actions.INFINIDATAddNewPoolFormAction;
import com.cloupia.feature.infinidat.reports.device.pools.actions.INFINIDATDeletePoolFormAction;
import com.cloupia.feature.infinidat.reports.device.pools.actions.INFINIDATEditPoolFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDevicePoolsReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDevicePoolsReport.class );
	private static final String NAME = "infinidat.device.pools.report";
	private static final String LABEL = "Pools";

	public INFINIDATDevicePoolsReport() {
		super();
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:INFINIDATDevicePoolsReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:getActions ####----" );}
		INFINIDATAddNewPoolFormAction newPoolAction = new INFINIDATAddNewPoolFormAction();
		INFINIDATEditPoolFormAction editPoolAction = new INFINIDATEditPoolFormAction();
		INFINIDATDeletePoolFormAction deletePoolAction = new INFINIDATDeletePoolFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 3 ];
		actions[ 0 ] = newPoolAction;
		actions[ 1 ] = editPoolAction;
		actions[ 2 ] = deletePoolAction;
		return actions;
	}
	@Override
	public Class<INFINIDATDevicePoolsReportImpl> getImplementationClass() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:getImplementationClass ####----" );}
		return INFINIDATDevicePoolsReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReport:getMapRules ####----" );}
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( context.getId() );
		rule.setContextType( context.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}