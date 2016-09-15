package com.cloupia.feature.infinidat.lovs;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.device.functions.INFINIDATFunctions;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.json.myJson;

public class INFINIDATClusterLunsLOV  implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATClusterLunsLOV.class );
	public static final String HOSTS_TABULAR_PROVIDER = "infinidat_cluster_luns_tabular_provider";

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {	
		String elements[] = context.getId().split(INFINIDATConstants.REPORT_DATA_SEPARATOR);
		String id = "0";
		String accName = "";
		if(elements.length == 2) {
			accName = elements[0];
			id = elements[1];
			logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- ID: " + id );
			logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- ACCNAME: " + accName );
		}
		
		INFINIDATFunctions func = new INFINIDATFunctions();
		func.setCredential(INFINIDATFunctions.getDeviceCredentials(accName));
		func.setAccountName(accName);
		
		String response = func.getAllLuns();
		String response2 = func.getAllClusterLuns();
		
		List<String> volumes = myJson.getElementAsArrayString(response,"result", "id");
		List<String> usedvolumes = new ArrayList<String>();
		
		for( int i = 0; i < myJson.getElementCountAsInt(response2,"result"); i++ ) {
			int count = myJson.getElementCountAsInt(response2,"result."+i+".luns");
			logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- ITERATION: " + i + " HAS COUNT: " + count );
			if(count > 0) {
				for(int j = 0; j < count; j++) {
					String lunid = myJson.getExactSingleItemAsString(response2,"result",i+".luns."+j+".id");
					String volumeid = myJson.getExactSingleItemAsString(response2,"result",i+".luns."+j+".volume_id");
					logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- LUN ID: " + lunid );
					logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- VOLUME ID: " + volumeid );
					usedvolumes.add(volumeid);
				}
			}
		}

		logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- GOT THE RESPONSES SUCCESSFULLY");

		logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- USED VOLUMES: " + usedvolumes);
		logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- USED VOLUMES SIZE: " + usedvolumes.size());
		logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- VOLUME: " + volumes.size());
		
		if(usedvolumes.size() > 0) {
			volumes.removeAll(usedvolumes);
		}
		
		logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- INTERESTING VOLUME COUNT: " + volumes.size());
		
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "Name", "Name" );
		model.completedHeader();
		
		int count = myJson.getElementCountAsInt(response,"result");
		logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- COUNT: " + count);
		for(int i = 0; i < volumes.size(); i++) {
			String interest = volumes.get(i);
			for(int j = 0; j < count - 1; j++) {
				String vid = myJson.getExactSingleItemAsString(response, "result", j + ".id");
				logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- INTERESTING VOLUME ID: " + interest + " CURRENT VOLUME ID: " + vid);
				if(vid.equals(interest)) {
					logger.info( "----#### INFINIDATClusterLunsLOV:getTabularReportReport ####---- ACTIVE VOLUME ID: " + vid);
					model.addTextValue(vid);
					model.addTextValue(myJson.getExactSingleItemAsString(response, "result", j + ".name"));
					model.completedRow();
				}
			}
		}
		model.updateReport( report );
		return report;
	}
}