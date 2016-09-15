package com.cloupia.feature.infinidat.lovs;

import org.apache.log4j.Logger;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;

public class SimpleTabularProvider implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( SimpleTabularProvider.class );
	public static final String SIMPLE_TABULAR_PROVIDER = "infinidat_tabular_provider";
	private static final int NUM_ROWS = 2;

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### SimpleTabularProvider:getTabularReportReport ####----" );
		logger.info( "----#### SimpleTabularProvider:getTabularReportReport ####---- CONTEXT: " + context );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Name", "Name" );
		model.addTextColumn( "Value", "Value" );
		model.completedHeader();
		for( int i = 0; i < NUM_ROWS; i++ ) {
			model.addTextValue( "name" + i );
			model.addTextValue( "value" + i );
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
