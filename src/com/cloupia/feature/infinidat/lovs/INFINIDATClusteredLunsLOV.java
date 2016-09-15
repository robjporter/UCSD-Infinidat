package com.cloupia.feature.infinidat.lovs;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.json.myJson;

public class INFINIDATClusteredLunsLOV  implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATClusteredLunsLOV.class );
	public static final String HOSTS_TABULAR_PROVIDER = "infinidat_clustered_luns_tabular_provider";

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {		
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			logger.info( "----#### INFINIDATClusteredLunsLOV:getTabularReportReport ####---- ID: " + id );
			logger.info( "----#### INFINIDATClusteredLunsLOV:getTabularReportReport ####---- ACCNAME: " + accName );
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String response = func.getClusterLuns(id);
		
		int count = myJson.getElementCountAsInt(response, "result");
		logger.info( "----#### INFINIDATClusteredLunsLOV:getTabularReportReport ####---- COUNT: " + count );
		
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "Name", "Name" );
		model.completedHeader();
		
		for( int i = 0; i < count; i++ ) {
			String lid = myJson.getExactSingleItemAsString(response, "result", i + ".volume_id");
			model.addTextValue(lid);
			model.addTextValue(func.getVolumeNameFromVolumeID(lid));
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}