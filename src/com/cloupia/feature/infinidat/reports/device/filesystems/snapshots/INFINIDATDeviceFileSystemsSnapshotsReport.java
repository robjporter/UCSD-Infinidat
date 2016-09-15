package com.cloupia.feature.infinidat.reports.device.filesystems.snapshots;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.filesystems.snapshots.actions.INFINIDATCloneFileSystemSnapshotFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.snapshots.actions.INFINIDATDeleteFileSystemSnapshotFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.snapshots.actions.INFINIDATRestoreSnapshotFileSystemFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATAddNewLunFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATDeleteLunFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATEditLunFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceFileSystemsSnapshotsReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceFileSystemsSnapshotsReport.class );
	private static final String NAME = "infinidat.device.filesystems.snapshots.report";
	private static final String LABEL = "Filesystem Snapshots";

	public INFINIDATDeviceFileSystemsSnapshotsReport() {
		super();
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:INFINIDATDeviceFileSystemsReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:getActions ####----" );
		INFINIDATCloneFileSystemSnapshotFormAction cloneFileSystemAction = new INFINIDATCloneFileSystemSnapshotFormAction();
		INFINIDATDeleteFileSystemSnapshotFormAction deleteFileSystemAction = new INFINIDATDeleteFileSystemSnapshotFormAction();
		INFINIDATRestoreSnapshotFileSystemFormAction restoreFileSystemAction = new INFINIDATRestoreSnapshotFileSystemFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 3 ];
		actions[ 0 ] = cloneFileSystemAction;
		actions[ 1 ] = deleteFileSystemAction;
		actions[ 2 ] = restoreFileSystemAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:getImplementationClass ####----" );
		return INFINIDATDeviceFileSystemsSnapshotsReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		logger.info( "----#### INFINIDATDeviceFileSystemsReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
