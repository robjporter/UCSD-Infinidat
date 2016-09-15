package com.cloupia.feature.infinidat.reports.charts.pie;

import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

@SuppressWarnings({"rawtypes","unused"})
public class INFINIDATReadWriteBytesReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( INFINIDATPieReportDiskPhysicalUsage.class );
	private static final String NAME = "infinidat.pie.report.read.write.bytes.usage";
	private static final String LABEL = "Bytes Usage Chart";

	public INFINIDATReadWriteBytesReport() {
		super();
		this.setMgmtColumnIndex( 0 );
	}
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:getImplementationClass ####----" );}
		return INFINIDATReadWriteBytesReportImpl.class;
	}
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:getReportLabel ####----" );}
		return LABEL;
	}
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:getReportName ####----" );}
		return NAME;
	}
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:isEasyReport ####----" );}
		return false;
	}
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:isLeafReport ####----" );}
		return true;
	}
	@Override
	public int getReportType() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:getReportType ####----" );}
		return ReportDefinition.REPORT_TYPE_SNAPSHOT;
	}
	@Override
	public int getReportHint() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:getReportHint ####----" );}
		return ReportDefinition.REPORT_HINT_PIECHART;
	}
	@Override
	public boolean showInSummary() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATPieReportDiskUsage:showInSummary ####----" );}
		return true;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceLunReport:getMapRules ####----" );}
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATDeviceLunReport:getMapRules ####---- REPORT CONTEXT: " + context );}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATDeviceLunReport:getMapRules ####---- REPORT CONTEXT: " + context.getId());}
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
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceLunReport:getContextLevel ####---- CONTEXT ID: " + context.getId() + " CONTEXT TYPE: " + context.getType());}
		return context.getType();
	}
}