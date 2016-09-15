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
public class INFINIDATPieReadWriteOpsReportImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATPieReportDiskPhysicalUsageImpl.class );

	@Override
	public SnapshotReport getSnapshotReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ####---- CONTEXT ID: " + context.getId()+context);}
		SnapshotReport report = new SnapshotReport();
        report.setContext( context );
        report.setReportName( reportEntry.getReportLabel());
        report.setNumericalData( true );
        report.setDisplayAsPie( true );
        report.setPrecision( 0 );   
        ReportNameValuePair[] rnv = new ReportNameValuePair[ 2 ];

        String accountName;
		if(context.getId().contains(";")){String[] parts = context.getId().split(";");accountName = parts[0];}else{accountName = context.getId();}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ACCOUNT NAME ####----" + accountName );}
		
		ObjStore<INFINIDATData> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATData.class );
		List<INFINIDATData> objs = dummyAssignStore.query( "accountName == '" + accountName + "'" );

		long readlatency_count = 0;
		long writelatency_count = 0;
		
		if( objs.size() == 1 ) {
			INFINIDATData pojo = objs.get( 0 );
			if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ####---- DATA:" + pojo.debug() );}
			readlatency_count = pojo.getReadOps();
			writelatency_count = pojo.getWriteOps();
		}
		
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ####---- RAW READ COUNT: " + readlatency_count);}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ####---- RAW WRITE COUNT: " + writelatency_count);}
		
		String data[] = myFormat.makeSmallerNumberRelativeToLargerWithMetric(readlatency_count, writelatency_count);
        
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ####---- DATA 0: " + data[0] );}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ####---- DATA 1: " + data[1] );}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReadWriteOpsReportImpl:getSnapshotReport ####---- DATA 2: " + data[2] );}
		
		if(data[2].equals("KB")) {data[2] = "K";} // Correction for number and not size, which is the default for function.
		
        rnv[ 0 ] = new ReportNameValuePair( "Read Ops " + data[2], Double.parseDouble(data[0]));
        rnv[ 1 ] = new ReportNameValuePair( "Write Ops " + data[2], Double.parseDouble(data[1]));

        SnapshotReportCategory cat = new SnapshotReportCategory();
        cat.setCategoryName( "Operations ps" );
        cat.setNameValuePairs( rnv );
        report.setCategories( new SnapshotReportCategory[] { cat });
        
		return report;
	}
}
