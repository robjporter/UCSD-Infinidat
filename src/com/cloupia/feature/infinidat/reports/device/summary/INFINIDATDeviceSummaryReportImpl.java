package com.cloupia.feature.infinidat.reports.device.summary;

import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATData;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.SummaryReportInternalModel;
import com.roporter.feature.format.myFormat;

@SuppressWarnings("unused")
public class INFINIDATDeviceSummaryReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceSummaryReportImpl.class );
	private static final String INFINIDAT_CHRONO_TABLE = "Device Data";
	private static final String INFINIDAT_NETWORK_TABLE = "Network Info";
	private static final String INFINIDAT_AVERGAES_TABLE = "Averages Info";
	private static final String[] GROUP_ORDER = { INFINIDAT_CHRONO_TABLE, INFINIDAT_NETWORK_TABLE  };

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceSummaryReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
        report.setContext( context );
        report.setGeneratedTime( System.currentTimeMillis());
        report.setReportName( reportEntry.getReportLabel());
        SummaryReportInternalModel model = new SummaryReportInternalModel();  

		String accName = context.getId().split(";")[0];
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeviceSummaryReportImpl:getTabularReportReport ####---- AccountName: " + accName );}
        ObjStore<INFINIDATData> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATData.class );
		List<INFINIDATData> summaryList = dummyAssignStore.query( " accountName == '" + accName + "'" );
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeviceSummaryReportImpl:getTabularReportReport ####---- SIZE: " + summaryList.size() );}

		if( summaryList.isEmpty() == false ) {
			INFINIDATData data = summaryList.get( 0 );
			if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATDeviceSummaryReportImpl:getTabularReportReport ####---- UPTIME: " + data.getUpTime());}

			model.addText( "System Date", myFormat.getDateFromTimestamp(data.getLocalTime()), INFINIDAT_CHRONO_TABLE );
	        model.addText( "System Time", myFormat.getTimeFromTimestamp(data.getLocalTime()), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Uptime Days", myFormat.safeIntToString(myFormat.getDateTimeDays(data.getUpTime())), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Uptime Hours", myFormat.safeIntToString(myFormat.getDateTimeHours(data.getUpTime())), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Uptime Mins", myFormat.safeIntToString(myFormat.getDateTimeMinutes(data.getUpTime())), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Uptime Secs", myFormat.safeIntToString(myFormat.getDateTimeSeconds(data.getUpTime())), INFINIDAT_CHRONO_TABLE );
	        model.addText( "System Version", data.getSystemVersion(), INFINIDAT_CHRONO_TABLE );
	        model.addText( "GUI Version", data.getGUIVersion(), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Infinishell Version", data.getShellVersion(), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Enclousure Count", myFormat.safeIntToString(data.getEnclousuresCount()), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Drive Count", myFormat.safeIntToString(data.getDriveCount()), INFINIDAT_CHRONO_TABLE );
	        model.addText( "Controller IP Address", data.getControllerIP(), INFINIDAT_NETWORK_TABLE );
	        model.addText( "Controller Netmask", data.getControllerNetmask(), INFINIDAT_NETWORK_TABLE );
	        model.addText( "Controller Gateway", data.getControllerGateway(), INFINIDAT_NETWORK_TABLE );
	        model.addText( "Controller Status", "OK", INFINIDAT_NETWORK_TABLE );
	        model.addText( "Volumes per Cluster", "2", INFINIDAT_AVERGAES_TABLE);
	        model.addText( "Hosts per Cluster", "2", INFINIDAT_AVERGAES_TABLE);
	        model.addText( "Lowest Average Read Latency", myFormat.safeDoubleToString(data.getLowestAverageReadLatency()), INFINIDAT_AVERGAES_TABLE);
	        model.addText( "Lowest Average Write Latency", myFormat.safeDoubleToString(data.getLowestAverageWriteLatency()), INFINIDAT_AVERGAES_TABLE);
	        model.addText( "Highest Average Read Latency", myFormat.safeDoubleToString(data.getHighestAverageReadLatency()), INFINIDAT_AVERGAES_TABLE);
	        model.addText( "Highest Average Write Latency", myFormat.safeDoubleToString(data.getHighestAverageWriteLatency()), INFINIDAT_AVERGAES_TABLE);
	        model.addText( "Enabled Local Users", myFormat.safeIntToString(data.getLocalUsersCount()), INFINIDAT_AVERGAES_TABLE);
		}
		
        model.setGroupOrder( GROUP_ORDER );
        model.updateReport( report );
        
        return report;
	}
}
