package com.cloupia.feature.infinidat.reports.charts.bar;

import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

@SuppressWarnings({"unused","rawtypes"})
public class INFINIDATBarReadWriteLatencyReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( INFINIDATBarReadWriteLatencyReport.class );
	private static final String NAME = "infinidat.bar.readwrite.latency";
	private static final String LABEL = "ms";
	
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:getImplementationClass ####----" );}
		return INFINIDATBarReadWriteLatencyReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:isLeafReport ####----" );}
		return true;
	}
	@Override
	public boolean showInSummary() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:showInSummary ####----" );}
		return true;
	}
	@Override
	public int getReportType() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:getReportType ####----" );}
		return ReportDefinition.REPORT_TYPE_SNAPSHOT;
	}
	@Override
	public int getReportHint() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:getReportHint ####----" );}
		return ReportDefinition.REPORT_HINT_BARCHART;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:getMapRules ####----" );}
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATBarReadWriteLatencyReport:getMapRules ####---- CONTEXT ID: " + context.getId());}
		ContextMapRule rule = new ContextMapRule();
		rule.setContextName( context.getId() );
		rule.setContextType( context.getType() );
		ContextMapRule[] rules = new ContextMapRule[ 1 ];
		rules[ 0 ] = rule;
		return rules;
	}
	@Override
	public int getContextLevel() {
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReport:getContextLevel ####---- CONTEXT ID: " + context.getId() + " TYPE: " + context.getType());}
		return context.getType();
	}
}
