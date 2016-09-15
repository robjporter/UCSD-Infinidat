package com.cloupia.feature.infinidat.reports.charts.bar;

import java.util.List;
import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATData;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.ReportNameValuePair;
import com.cloupia.model.cIM.SnapshotReport;
import com.cloupia.model.cIM.SnapshotReportCategory;
import com.cloupia.service.cIM.inframgr.SnapshotReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;

@SuppressWarnings("unused")
public class INFINIDATBarReadWriteLatencyReportImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATBarReadWriteLatencyReportImpl.class );
	
	private final int NUM_BARS = 2;
	private final String BAR_1 = "Read";
	private final String BAR_2 = "Write";

	@Override
	public SnapshotReport getSnapshotReport(ReportRegistryEntry reportEntry, ReportContext context) throws Exception {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####----" );}
		SnapshotReport report = new SnapshotReport();
        report.setContext(context);
        report.setReportName(reportEntry.getReportLabel());
        report.setNumericalData(true);
        report.setValueAxisName("Latency ms");
        report.setPrecision(0);
        
        String accountName;
		if(context.getId().contains(";")){String[] parts = context.getId().split(";");accountName = parts[0];}else{accountName = context.getId();}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ACCOUNT NAME ####---- " + accountName );}
		
		ObjStore<INFINIDATData> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATData.class );
		List<INFINIDATData> objs = dummyAssignStore.query( "accountName == '" + accountName + "'" );

		long readlatency_count = 0;
		long writelatency_count = 0;
		long readops_count = 0;
		long writeops_count = 0;
		
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- SIZE: " + objs.size() );}
		
		if( objs.size() == 1 ) {
			INFINIDATData pojo = objs.get( 0 );
			if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- DATA:" + pojo.debug() );}
			readlatency_count = pojo.getReadLatency();
			writelatency_count = pojo.getWriteLatency();			
			readops_count = pojo.getReadOps();
			writeops_count = pojo.getWriteOps();
		}

		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW READ LATENCY COUNT: " + readlatency_count);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW WRITE LATENCY COUNT: " + writelatency_count);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW READ OPS COUNT: " + readops_count);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW WRITE OPS COUNT: " + writeops_count);}
		
		double readLatencyResult = 1;
		double writeLatencyResult = 1;
		if(readlatency_count > 0 && readops_count > 0) {
			readLatencyResult = readlatency_count / readops_count;
		}
		if(writelatency_count > 0 && writeops_count > 0) {
			writeLatencyResult = writelatency_count / writeops_count;
		}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW READ MICROSECONDS: " + readLatencyResult);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW WRITE MICROSECONDS: " + writeLatencyResult);}

		readLatencyResult = readLatencyResult / 1000;
		writeLatencyResult = writeLatencyResult / 1000;
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW READ MILLISECONDS: " + readLatencyResult);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW WRITE MILLISECONDS: " + writeLatencyResult);}
		
        ReportNameValuePair[] rnv1 = new ReportNameValuePair[NUM_BARS];
        if( objs.size() == 1 ) {
			INFINIDATData pojo = objs.get( 0 );
			if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATBarReadWriteLatencyReportImpl:getSnapshotReport SIZE ####----" + pojo );}
	        rnv1[0] = new ReportNameValuePair(BAR_1, readLatencyResult);
	        rnv1[1] = new ReportNameValuePair(BAR_2, writeLatencyResult);
		}

        SnapshotReportCategory cat1 = new SnapshotReportCategory();
        cat1.setCategoryName("Latency");
        cat1.setNameValuePairs(rnv1);
        
        report.setCategories(new SnapshotReportCategory[] { cat1 });
        
		return report;
	}
}
