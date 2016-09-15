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

public class INFINIDATHostPorts  implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATHostPorts.class );
	public static final String HOST_PORT_TABULAR_PROVIDER = "infinidat_host_port_tabular_provider";

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			logger.info( "----#### INFINIDATHostPorts:getTabularReportReport ####---- ID: " + id );
			logger.info( "----#### INFINIDATHostPorts:getTabularReportReport ####---- ACCNAME: " + accName );
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String response = func.getAllHostPorts(id);
		int count = myJson.getElementCountAsInt(response, "result");
		logger.info( "----#### INFINIDATHostPorts:getTabularReportReport ####---- COUNT: " + count );
		
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "WWPN", "WWPN" );
		model.addTextColumn( "Type", "Type" );
		model.completedHeader();
		
		for( int i = 0; i < count; i++ ) {
			String type = myJson.getExactSingleItemAsString(response, "result", i + ".type");
			String wwpn = myJson.getExactSingleItemAsString(response, "result", i + ".address");
			model.addTextValue(wwpn);
			model.addTextValue(type);
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}