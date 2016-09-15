package com.cloupia.feature.infinidat.reports.device.volume.clones;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.volume.clones.actions.INFINIDATDeleteVolumeCloneFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceVolumeClonesReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceVolumeClonesReport.class );
	private static final String NAME = "infinidat.device.volume.clones.report";
	private static final String LABEL = "Volume Clones";

	public INFINIDATDeviceVolumeClonesReport() {
		super();
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:INFINIDATDeviceVolumeReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:getActions ####----" );
		INFINIDATDeleteVolumeCloneFormAction deleteVolumeCloneAction = new INFINIDATDeleteVolumeCloneFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 1 ];
		actions[ 0 ] = deleteVolumeCloneAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:getImplementationClass ####----" );
		return INFINIDATDeviceVolumeClonesReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		logger.info( "----#### INFINIDATDeviceVolumeClonesReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
