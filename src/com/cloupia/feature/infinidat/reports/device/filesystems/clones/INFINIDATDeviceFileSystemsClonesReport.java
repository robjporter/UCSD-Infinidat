package com.cloupia.feature.infinidat.reports.device.filesystems.clones;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.filesystems.clones.actions.INFINIDATDeleteFileSystemCloneFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceFileSystemsClonesReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceFileSystemsClonesReport.class );
	private static final String NAME = "infinidat.device.filesystems.clones.report";
	private static final String LABEL = "Filesystem Clones";

	public INFINIDATDeviceFileSystemsClonesReport() {
		super();
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:INFINIDATDeviceFileSystemsClonesReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:getActions ####----" );
		INFINIDATDeleteFileSystemCloneFormAction deleteFileSystemCloneAction = new INFINIDATDeleteFileSystemCloneFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 1 ];
		actions[ 0 ] = deleteFileSystemCloneAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:getImplementationClass ####----" );
		return INFINIDATDeviceFileSystemsClonesReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		logger.info( "----#### INFINIDATDeviceFileSystemsClonesReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
