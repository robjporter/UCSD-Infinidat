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
public class INFINIDATBarCountsReportImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATBarCountsReportImpl.class );
	
	private final int NUM_BARS = 9;
	private final String BAR_1 = "Node Count";
	private final String BAR_2 = "Host Count";
	private final String BAR_3 = "Pool Count";
	private final String BAR_4 = "Volume Count";
	private final String BAR_5 = "Snapshot Count";
	private final String BAR_6 = "Clone Count";
	private final String BAR_7 = "Cluster Count";
	private final String BAR_8 = "FileSystem Count";
	private final String BAR_9 = "Export Count";

	@Override
	public SnapshotReport getSnapshotReport(ReportRegistryEntry reportEntry, ReportContext context) throws Exception {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarCountsReportImpl:getSnapshotReport ####----" );}
		SnapshotReport report = new SnapshotReport();
        report.setContext(context);
        report.setReportName(reportEntry.getReportLabel());
        report.setNumericalData(true);
        report.setValueAxisName(INFINIDATConstants.REPORT_GRAPH_BAR_COUNTS_AXIS_NAME);
        report.setPrecision(0);
        
        String accountName;
		if(context.getId().contains(";")){String[] parts = context.getId().split(";");accountName = parts[0];}else{accountName = context.getId();}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarCountsReportImpl:getSnapshotReport ACCOUNT NAME ####---- " + accountName );}
		
		ObjStore<INFINIDATData> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATData.class );
		List<INFINIDATData> objs = dummyAssignStore.query( "accountName == '" + accountName + "'" );
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarCountsReportImpl:getSnapshotReport SIZE ####---- " + objs.size() );}
        
        ReportNameValuePair[] rnv1 = new ReportNameValuePair[NUM_BARS];
        if( objs.size() == 1 ) {
			INFINIDATData pojo = objs.get( 0 );
			if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATBarCountsReportImpl:getSnapshotReport SIZE ####----" + pojo );}
	        rnv1[0] = new ReportNameValuePair(BAR_1, pojo.getNodeCount());
	        rnv1[1] = new ReportNameValuePair(BAR_2, pojo.getHostCount());
	        rnv1[2] = new ReportNameValuePair(BAR_3, pojo.getPoolCount());
	        rnv1[3] = new ReportNameValuePair(BAR_4, pojo.getVolumeCount());
	        rnv1[4] = new ReportNameValuePair(BAR_5, pojo.getVolumeSnapshotCount());
	        rnv1[5] = new ReportNameValuePair(BAR_6, pojo.getCloneCount());
	        rnv1[6] = new ReportNameValuePair(BAR_7, pojo.getClusterCount());
	        rnv1[7] = new ReportNameValuePair(BAR_8, pojo.getFileSystemCount());
	        rnv1[8] = new ReportNameValuePair(BAR_9, pojo.getExportCount());
		}

        SnapshotReportCategory cat1 = new SnapshotReportCategory();
        cat1.setCategoryName(INFINIDATConstants.REPORT_GRAPH_BAR_COUNTS_CATEGORY);
        cat1.setNameValuePairs(rnv1);
        
        report.setCategories(new SnapshotReportCategory[] { cat1 });
        
		return report;
	}

}
