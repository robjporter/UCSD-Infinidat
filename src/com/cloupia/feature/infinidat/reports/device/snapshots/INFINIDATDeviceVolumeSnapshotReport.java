package com.cloupia.feature.infinidat.reports.device.snapshots;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.snapshots.actions.INFINIDATCloneVolumeSnapshotFormAction;
import com.cloupia.feature.infinidat.reports.device.snapshots.actions.INFINIDATDeleteVolumeSnapshotFormAction;
import com.cloupia.feature.infinidat.reports.device.snapshots.actions.INFINIDATEditVolumeSnapshotFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceVolumeSnapshotReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceVolumeSnapshotReport.class );
	private static final String NAME = "infinidat.device.volume.snapshot.report";
	private static final String LABEL = "Volume Snapshots";

	public INFINIDATDeviceVolumeSnapshotReport() {
		super();
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:INFINIDATDeviceVolumeSnapshotReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:getActions ####----" );
		INFINIDATEditVolumeSnapshotFormAction editSnapshotAction = new INFINIDATEditVolumeSnapshotFormAction();
		INFINIDATDeleteVolumeSnapshotFormAction deleteSnapshotAction = new INFINIDATDeleteVolumeSnapshotFormAction();
		INFINIDATCloneVolumeSnapshotFormAction cloneSnapshotAction = new INFINIDATCloneVolumeSnapshotFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 3 ];
		actions[ 0 ] = editSnapshotAction;
		actions[ 1 ] = deleteSnapshotAction;
		actions[ 2 ] = cloneSnapshotAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:getImplementationClass ####----" );
		return INFINIDATDeviceVolumeSnapshotReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
