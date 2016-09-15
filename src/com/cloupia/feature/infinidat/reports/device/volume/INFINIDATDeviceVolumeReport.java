package com.cloupia.feature.infinidat.reports.device.volume;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.device.volume.actions.INFINIDATAddNewVolumeFormAction;
import com.cloupia.feature.infinidat.reports.device.volume.actions.INFINIDATCloneVolumeFormAction;
import com.cloupia.feature.infinidat.reports.device.volume.actions.INFINIDATCreateVolumeSnapshotFormAction;
import com.cloupia.feature.infinidat.reports.device.volume.actions.INFINIDATDeleteVolumeFormAction;
import com.cloupia.feature.infinidat.reports.device.volume.actions.INFINIDATEditVolumeFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATAddNewLunFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATDeleteLunFormAction;
//import com.cloupia.feature.infinidat.forms.actions.lun.INFINIDATEditLunFormAction;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportAction;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReportWithActions;

public class INFINIDATDeviceVolumeReport extends CloupiaReportWithActions {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceVolumeReport.class );
	private static final String NAME = "infinidat.device.volume.report";
	private static final String LABEL = "Volumes";

	public INFINIDATDeviceVolumeReport() {
		super();
		logger.info( "----#### INFINIDATDeviceVolumeReport:INFINIDATDeviceVolumeReport ####----" );
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public CloupiaReportAction[] getActions() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:getActions ####----" );
		INFINIDATAddNewVolumeFormAction newVolumeAction = new INFINIDATAddNewVolumeFormAction();
		INFINIDATEditVolumeFormAction editVolumeAction = new INFINIDATEditVolumeFormAction();
		INFINIDATDeleteVolumeFormAction deleteVolumeAction = new INFINIDATDeleteVolumeFormAction();
		INFINIDATCreateVolumeSnapshotFormAction createVolumeSnapshot = new INFINIDATCreateVolumeSnapshotFormAction();
		INFINIDATCloneVolumeFormAction cloneVolumeAction = new INFINIDATCloneVolumeFormAction();
		CloupiaReportAction[] actions = new CloupiaReportAction[ 4 ];
		actions[ 0 ] = newVolumeAction;
		actions[ 1 ] = editVolumeAction;
		actions[ 2 ] = deleteVolumeAction;
		actions[ 3 ] = createVolumeSnapshot;
		//actions[ 4 ] = cloneVolumeAction;
		return actions;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:getImplementationClass ####----" );
		return INFINIDATDeviceVolumeReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:getReportLabel ####----" );
		return LABEL;
	}
	@Override
	public String getReportName() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:getReportName ####----" );
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:isEasyReport ####----" );
		return false;
	}
	@Override
	public boolean isLeafReport() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:isLeafReport ####----" );
		return false;
	}
	@Override
	public int getMenuID() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:getMenuID ####----" );
		return 51;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		logger.info( "----#### INFINIDATDeviceVolumeReport:getMapRules ####----" );
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
