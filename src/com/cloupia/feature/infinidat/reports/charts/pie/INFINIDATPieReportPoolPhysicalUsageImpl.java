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
public class INFINIDATPieReportPoolPhysicalUsageImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATPieReportPoolPhysicalUsageImpl.class );

	@Override
	public SnapshotReport getSnapshotReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATPieReportPoolPhysicalUsageImpl:getSnapshotReport ####---- CONTEXT ID: " + context.getId()+context);}
		SnapshotReport report = new SnapshotReport();
        report.setContext( context );
        report.setReportName( reportEntry.getReportLabel());
        report.setNumericalData( true );
        report.setDisplayAsPie( true );
        report.setPrecision( 0 );   
        ReportNameValuePair[] rnv = new ReportNameValuePair[ 2 ];

        String accountName;
		if(context.getId().contains(";")){String[] parts = context.getId().split(";");accountName = parts[0];}else{accountName = context.getId();}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReportPoolPhysicalUsageImpl:getSnapshotReport ####---- ACCOUNT NAME:" + accountName );}
		
		ObjStore<INFINIDATData> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATData.class );
		List<INFINIDATData> objs = dummyAssignStore.query( "accountName == '" + accountName + "'" );

		long used_count = 0;
		long free_count = 0;
		
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReportPoolPhysicalUsageImpl:getSnapshotReport ####---- SIZE: " + objs.size() );}
		
		if( objs.size() == 1 ) {
			INFINIDATData pojo = objs.get( 0 );
			if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATPieReportPoolPhysicalUsageImpl:getSnapshotReport ####---- DATA: " + pojo.debug() );}
			used_count = pojo.getPhysicalPoolCapacity();
			free_count = pojo.getPhysicalPoolFree();
		}
		
		String data[] = myFormat.makeSmallerNumberRelativeToLargerWithMetric(used_count-free_count, free_count);
        
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReportPoolPhysicalUsageImpl:getSnapshotReport ####---- DATA 0: " + data[0] );}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReportPoolPhysicalUsageImpl:getSnapshotReport ####---- DATA 1: " + data[1] );}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATPieReportPoolPhysicalUsageImpl:getSnapshotReport ####---- DATA 2: " + data[2] );}
		
        rnv[ 0 ] = new ReportNameValuePair( "Free Space in " + data[2], Double.parseDouble(data[1]));
        rnv[ 1 ] = new ReportNameValuePair( "Used Space in " + data[2], Double.parseDouble(data[0]));
        

        SnapshotReportCategory cat = new SnapshotReportCategory();
        cat.setCategoryName(INFINIDATConstants.REPORT_GRAPH_PIE_PHYSICAL_USAGE_LABEL);
        cat.setNameValuePairs( rnv );
        report.setCategories( new SnapshotReportCategory[] { cat });
        
		return report;
	}
}
