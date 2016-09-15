package com.cloupia.feature.infinidat.reports.device.filesystems.exports;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions.INFINIDATDeleteFileSystemExportFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions.INFINIDATDisableFileSystemExportFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions.INFINIDATEditFileSystemExportFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions.INFINIDATDeleteFileSystemExportPermissionFormAction;
import com.cloupia.feature.infinidat.reports.device.filesystems.exports.actions.INFINIDATEnableFileSystemExportFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

@SuppressWarnings("unused")
public class INFINIDATDeviceFileSystemsExportsReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceFileSystemsExportsReport.class );
	private static final String NAME = "infinidat.device.filesystems.exports.report";
	private static final String LABEL = "Filesystem Exports";

	public INFINIDATDeviceFileSystemsExportsReport() {
		super();
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:INFINIDATDeviceFileSystemsExportsReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:getActions ####----" );}
		INFINIDATDeleteFileSystemExportFormAction deleteFileSystemExportAction = new INFINIDATDeleteFileSystemExportFormAction();
		INFINIDATEditFileSystemExportFormAction editFileSystemExportAction = new INFINIDATEditFileSystemExportFormAction();
		INFINIDATDeleteFileSystemExportPermissionFormAction editFileSystemExportPermissionsAction = new INFINIDATDeleteFileSystemExportPermissionFormAction();
		INFINIDATEnableFileSystemExportFormAction enableFileSystemExportAction = new INFINIDATEnableFileSystemExportFormAction();
		INFINIDATDisableFileSystemExportFormAction disableFileSystemExportAction = new INFINIDATDisableFileSystemExportFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 5 ];
		actions[ 0 ] = deleteFileSystemExportAction;
		actions[ 1 ] = editFileSystemExportAction;
		actions[ 2 ] = editFileSystemExportPermissionsAction;
		actions[ 3 ] = enableFileSystemExportAction;
		actions[ 4 ] = disableFileSystemExportAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:getImplementationClass ####----" );}
		return INFINIDATDeviceFileSystemsExportsReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:isLeafReport ####----" );}
		return false;
	}
	@Override
	public int getMenuID() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:getMenuID ####----" );}
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
