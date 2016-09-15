package com.cloupia.feature.infinidat.reports.device.filesystems;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.filesystems.actions.INFINIDATAddNewFileSystemFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.actions.INFINIDATCreateFileSystemSnapshotFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.actions.INFINIDATDeleteFileSystemFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.actions.INFINIDATEditFileSystemFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.actions.INFINIDATExportFileSystemFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATAddNewLunFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATDeleteLunFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATEditLunFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceFileSystemsReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceFileSystemsReport.class );
	private static final String NAME = "infinidat.device.filesystems.report";
	private static final String LABEL = "Filesystems";

	public INFINIDATDeviceFileSystemsReport() {
		super();
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:INFINIDATDeviceFileSystemsReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:getActions ####----" );}
		INFINIDATAddNewFileSystemFormAction newFileSystemAction = new INFINIDATAddNewFileSystemFormAction();
		INFINIDATEditFileSystemFormAction editFileSystemAction = new INFINIDATEditFileSystemFormAction();
		INFINIDATDeleteFileSystemFormAction deleteFileSystemAction = new INFINIDATDeleteFileSystemFormAction();
		INFINIDATCreateFileSystemSnapshotFormAction createFileSystemSnapshotAction = new INFINIDATCreateFileSystemSnapshotFormAction();
		INFINIDATExportFileSystemFormAction createFileSystemExportAction = new INFINIDATExportFileSystemFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 5 ];
		actions[ 0 ] = newFileSystemAction;
		actions[ 1 ] = editFileSystemAction;
		actions[ 2 ] = deleteFileSystemAction;
		actions[ 3 ] = createFileSystemSnapshotAction;
		actions[ 4 ] = createFileSystemExportAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:getImplementationClass ####----" );}
		return INFINIDATDeviceFileSystemsReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
