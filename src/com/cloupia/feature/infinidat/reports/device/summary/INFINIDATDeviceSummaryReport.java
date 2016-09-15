package com.cloupia.feature.infinidat.reports.device.summary;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

public class INFINIDATDeviceSummaryReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceSummaryReport.class );
	private static final String NAME = "INFINIDAT.device.summary.report";
	private static final String LABEL = "Device Summary";

	public INFINIDATDeviceSummaryReport() {
		super();
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:INFINIDATDeviceSummaryReport ####----" );}
		this.setMgmtColumnIndex( 0 );
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:getImplementationClass ####----" );}
		return INFINIDATDeviceSummaryReportImpl.class;
	}
	@Override
	public int getMenuID() {
		return 51;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:isLeafReport ####----" );}
		return true;
	}
	@Override
	public int getReportType() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:getReportType ####----" );}
		return ReportDefinition.REPORT_TYPE_SUMMARY;
	}
	@Override
	public int getReportHint() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:getReportHint ####----" );}
		return ReportDefinition.REPORT_HINT_VERTICAL_TABLE_WITH_GRAPHS;
	}
	@Override
	public boolean isManagementReport() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummayReport:isManagementReport ####----" );}
		return true;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummaryReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
}
