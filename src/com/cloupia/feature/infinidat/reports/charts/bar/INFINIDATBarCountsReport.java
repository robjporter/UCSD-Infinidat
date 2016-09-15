package com.cloupia.feature.infinidat.reports.charts.bar;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.ReportDefinition;
import com.cloupia.service.cIM.inframgr.reportengine.ContextMapRule;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaNonTabularReport;

@SuppressWarnings("unused")
public class INFINIDATBarCountsReport extends CloupiaNonTabularReport {
	private static Logger logger = Logger.getLogger( INFINIDATBarCountsReport.class );
	private static final String NAME = "infinidat.bar.report.counts";
	private static final String LABEL = "Element Counts";
	
   /**
    * @return BarChartReport implementation class type 
    */
	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplementationClass() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:getImplementationClass ####----" );}
		return INFINIDATBarCountsReportImpl.class;
	}
	/**
	 * @return Report label
	 */
	@Override
	public String getReportLabel() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:getReportLabel ####----" );}
		return LABEL;
	}
    /**
     * @return report name
     */
	@Override
	public String getReportName() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:getReportName ####----" );}
		return NAME;
	}
    /**
     * @return true only if the report implementation followed POJO approach 
     */
	@Override
	public boolean isEasyReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:isEasyReport ####----" );}
		return false;
	}
    /**
     * @return true if the report does not have any drillDown report
     */
	@Override
	public boolean isLeafReport() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:isLeafReport ####----" );}
		return true;
	}
	/**
	*@return true if you want this chart to show up in a summary report
	*/
	@Override
	public boolean showInSummary() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:showInSummary ####----" );}
		return true;
	}
	/** 
	 * @return report type like tabular report,snapshot report ,summary report etc.
	 */
	@Override
	public int getReportType() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:getReportType ####----" );}
		return ReportDefinition.REPORT_TYPE_SNAPSHOT;
	}
	/** 
	 * @return report hint
	 */
	@Override
	public int getReportHint() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:getReportHint ####----" );}
		return ReportDefinition.REPORT_HINT_BARCHART;
	}
	@Override
	public ContextMapRule[] getMapRules() {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:getMapRules ####----" );}
		DynReportContext context = ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info("----#### INFINIDATBarCountsReport:getMapRules ####---- CONTEXT ID: " + context.getId());}
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
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReport:getContextLevel ####---- CONTEXT ID: " + context.getId() + " TYPE: " + context.getType());}
		return context.getType();
	}
}
