package com.cloupia.feature.infinidat.reports.charts.pie;

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
import com.roporter.feature.format.myFormat;

@SuppressWarnings("unused")
public class INFINIDATPieReadWriteLatencyReportImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATPieReportDiskPhysicalUsageImpl.class );

	@Override
	public SnapshotReport getSnapshotReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- CONTEXT ID: " + context.getId());}
		SnapshotReport report = new SnapshotReport();
        report.setContext( context );
        report.setReportName( reportEntry.getReportLabel());
        report.setNumericalData( true );
        report.setDisplayAsPie( true );
        report.setPrecision( 0 );   
        ReportNameValuePair[] rnv = new ReportNameValuePair[ 2 ];

        String accountName;
		if(context.getId().contains(";")){String[] parts = context.getId().split(";");accountName = parts[0];}else{accountName = context.getId();}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ACCOUNT NAME ####----" + accountName );}
		
		ObjStore<INFINIDATData> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATData.class );
		List<INFINIDATData> objs = dummyAssignStore.query( "accountName == '" + accountName + "'" );

		long readlatency_count = 0;
		long writelatency_count = 0;
		long readops_count = 0;
		long writeops_count = 0;
		
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- SIZE: " + objs.size() );}
		
		if( objs.size() == 1 ) {
			INFINIDATData pojo = objs.get( 0 );
			if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- DATA:" + pojo.debug() );}
			readlatency_count = pojo.getReadLatency();
			writelatency_count = pojo.getWriteLatency();			
			readops_count = pojo.getReadOps();
			writeops_count = pojo.getWriteOps();
		}

		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW READ LATENCY COUNT: " + readlatency_count);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW WRITE LATENCY COUNT: " + writelatency_count);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW READ OPS COUNT: " + readops_count);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW WRITE OPS COUNT: " + writeops_count);}
		
		double readLatencyResult = 1;
		double writeLatencyResult = 1;
		if(readlatency_count > 0 && readops_count > 0) {
			readLatencyResult = readlatency_count / readops_count;
		}
		if(writelatency_count > 0 && writeops_count > 0) {
			writeLatencyResult = writelatency_count / writeops_count;
		}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW READ COUNT: " + readLatencyResult);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteLatencyReportImpl:getSnapshotReport ####---- RAW WRITE COUNT: " + writeLatencyResult);}
		      
        rnv[ 0 ] = new ReportNameValuePair( "Read Latency ms", readLatencyResult);
        rnv[ 1 ] = new ReportNameValuePair( "Write Latency ms", writeLatencyResult);

        SnapshotReportCategory cat = new SnapshotReportCategory();
        cat.setCategoryName( "Latency ms" );
        cat.setNameValuePairs( rnv );
        report.setCategories( new SnapshotReportCategory[] { cat });
        
		return report;
	}
}
