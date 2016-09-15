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
public class INFINIDATBarEventErrorsReportImpl implements SnapshotReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATBarEventErrorsReportImpl.class );
	
	private final int NUM_BARS = 3;
	private final String BAR_1 = "Warning";
	private final String BAR_2 = "Error";
	private final String BAR_3 = "Critical";

	@Override
	public SnapshotReport getSnapshotReport(ReportRegistryEntry reportEntry, ReportContext context) throws Exception {
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATBarEventErrorsReportImpl:getSnapshotReport ####----" );}
		SnapshotReport report = new SnapshotReport();
        report.setContext(context);
        report.setReportName(reportEntry.getReportLabel());
        report.setNumericalData(true);
        report.setValueAxisName("Event Count");
        report.setPrecision(0);
        
        String accountName;
		if(context.getId().contains(";")){String[] parts = context.getId().split(";");accountName = parts[0];}else{accountName = context.getId();}
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarEventErrorsReportImpl:getSnapshotReport ACCOUNT NAME ####---- " + accountName );}
		
		ObjStore<INFINIDATData> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATData.class );
		List<INFINIDATData> objs = dummyAssignStore.query( "accountName == '" + accountName + "'" );
		if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATBarEventErrorsReportImpl:getSnapshotReport SIZE ####---- " + objs.size() );}
        
        //this is my first set of data
        ReportNameValuePair[] rnv1 = new ReportNameValuePair[NUM_BARS];
        if( objs.size() == 1 ) {
			INFINIDATData pojo = objs.get( 0 );
			if(INFINIDATConstants.DEBUG_GRAPHS && INFINIDATConstants.DEBUG2){logger.info( "----#### INFINIDATBarCountsReportImpl:getSnapshotReport SIZE ####----" + pojo );}
	        rnv1[0] = new ReportNameValuePair(BAR_1, pojo.getWarningCount());
	        rnv1[1] = new ReportNameValuePair(BAR_2, pojo.getErrorCount());
	        rnv1[2] = new ReportNameValuePair(BAR_3, pojo.getCriticalCount());
		}

        SnapshotReportCategory cat1 = new SnapshotReportCategory();
        cat1.setCategoryName("Events");
        cat1.setNameValuePairs(rnv1);
        
        report.setCategories(new SnapshotReportCategory[] { cat1 });
        
		return report;
	}
}
