package com.cloupia.feature.infinidat.reports.charts.pie;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

@SuppressWarnings({"rawtypes","unused"})
public class INFINIDATPieReadWriteLatencyReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( INFINIDATPieReadWriteLatencyReport.class );
	private static final String NAME = "infinidat.pie.report.read.write.latency.usage";
	private static final String LABEL = "Average Latency Chart";

	public INFINIDATPieReadWriteLatencyReport() {
		super();
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:getImplementationClass ####----" );}
		return INFINIDATPieReadWriteLatencyReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:isLeafReport ####----" );}
		return true;
	}
	@Override
	public int getReportType() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:getReportType ####----" );}
		return ReportDefinition.REPORT_TYPE_SNAPSHOT;
	}
	@Override
	public int getReportHint() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:getReportHint ####----" );}
		return ReportDefinition.REPORT_HINT_PIECHART;
	}
	@Override
	public boolean showInSummary() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:showInSummary ####----" );}
		return true;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:getMapRules ####----" );}
		DynReportContext dummyContextOneType = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( dummyContextOneType.getId() );
		rule.setContextType( dummyContextOneType.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
	@Override
	public int getContextLevel() {
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReadWriteLatencyReport:getContextLevel ####---- CONTEXT ID: " + context.getId() + " TYPE: " + context.getType());}
		return context.getType();
	}
}