package com.cloupia.feature.infinidat.reports.device.clusters;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATClusters;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.format.myFormat;

@PersistenceCapable( detachable = "true", table = "infinidat_cluster_capability")
public class INFINIDATDeviceClusterReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceClusterReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_CLUSTERS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceClusterReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATClusters> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATClusters.class );
		List<INFINIDATClusters> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );

		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "secret", "secret", true );
		model.addTextColumn( "Cluster ID", "Cluster ID" );
		model.addTextColumn( "Name", "Name" );
		model.addTextColumn( "Volumes", "Volumes" );
		model.addTextColumn( "Hosts", "Hosts" );
		model.addTextColumn( "Created At", "Created At" );
		model.addTextColumn( "Updated At", "Updated At" );
		model.addTextColumn( "Comment", "Comment" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATClusters pojo = objs.get( i );
			model.addTextValue(pojo.getAccountName() + INFINIDATConstants.REPORT_DATA_SEPARATOR + myFormat.safeIntToString(pojo.getID()));
			model.addTextValue(myFormat.safeIntToString(pojo.getID()));
			model.addTextValue(pojo.getName());
			model.addTextValue(myFormat.safeIntToString(pojo.getLunCount()));
			model.addTextValue(myFormat.safeIntToString(pojo.getHostCount()));
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getCreateDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getCreateDate()));
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getUpdateDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getUpdateDate()));
			model.addTextValue(pojo.getComment());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
