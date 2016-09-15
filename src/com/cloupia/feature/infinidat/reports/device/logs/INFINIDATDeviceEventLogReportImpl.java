package com.cloupia.feature.infinidat.reports.device.logs;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATEventLog;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.format.myFormat;

@SuppressWarnings("unused")
public class INFINIDATDeviceEventLogReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceEventLogReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_LOGS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceEventLogReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATEventLog> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATEventLog.class );
		List<INFINIDATEventLog> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "Reporter", "Reporter" );
		model.addTextColumn( "Date/Time", "Date/Time" );
		model.addTextColumn( "Level", "Level" );
		model.addTextColumn( "Visibility", "Visibility" );
		model.addTextColumn( "Node", "Node" );
		model.addTextColumn( "Version", "Version" );
		model.addTextColumn( "Description", "Description" );
		
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATEventLog pojo = objs.get( i );
			model.addTextValue( Integer.toString(pojo.getID()));
			model.addTextValue( pojo.getReporter());
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getDateTime()) + " " + myFormat.getTimeFromTimestamp(pojo.getDateTime()));
			model.addTextValue( pojo.getLevel());
			model.addTextValue( pojo.getVisibility());
			model.addTextValue( myFormat.safeIntToString(pojo.getNode()));
			model.addTextValue( pojo.getVersion());
			model.addTextValue( pojo.getDescription());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
