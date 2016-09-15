package com.cloupia.feature.infinidat.reports.device.pools;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

//import javax.jdo.annotations.PersistenceCapable;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATPools;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.format.myFormat;

@PersistenceCapable( detachable = "true", table = "infinidat_device_pools_controller_report")
public class INFINIDATDevicePoolsReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDevicePoolsReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_POOLS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevicePoolsReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATPools> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATPools.class );
		List<INFINIDATPools> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "secret", "secret", true);
		model.addTextColumn( "Pool ID", "Pool ID" );
		model.addTextColumn( "Name", "Name" );
		model.addTextColumn( "State", "State" );
		model.addTextColumn( "Physical Capacity", "Physical Capacity" );
		model.addTextColumn( "Virtual Capacity", "Virtual Capacity" );
		model.addTextColumn( "Capacity Warning", "Capacity Warning" );
		model.addTextColumn( "Critical Warning", "Critical Warning" );
		model.addTextColumn( "Reserved Capacity", "Reserved Capacity" );
		model.addTextColumn( "Entity Count", "Entity Count" );
		model.addTextColumn( "Volume Count", "Volume Count" );
		model.addTextColumn( "Filesystem Count", "Filesystem Count" );
		model.addTextColumn( "Snapshot Count", "Snapshot Count" );
		model.addTextColumn( "Clone Count", "Clone Count" );
		model.addTextColumn( "Created At", "Created At" );
		model.addTextColumn( "Comment", "Comment" );
		model.completedHeader();
		
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATPools pojo = objs.get( i );
			model.addTextValue(pojo.getAccountName() + INFINIDATConstants.REPORT_DATA_SEPARATOR + myFormat.safeIntToString(pojo.getPoolID()));
			model.addTextValue( myFormat.safeIntToString(pojo.getPoolID()));
			model.addTextValue( pojo.getName());
			model.addTextValue( pojo.getState());
			model.addTextValue( myFormat.humanReadableFromLong(pojo.getPhysicalCapacity(),true));
			model.addTextValue( myFormat.humanReadableFromLong(pojo.getVirtualCapacity(),true));
			model.addTextValue( myFormat.safeIntToString(pojo.getCapacityWarning()));
			model.addTextValue( myFormat.safeIntToString(pojo.getCapacityCritical()));
			model.addTextValue( myFormat.humanReadableFromLong(pojo.getReservedCapacity(),true));
			model.addTextValue( myFormat.safeIntToString(pojo.getEntityCount()));
			model.addTextValue( myFormat.safeIntToString(pojo.getVolumeCount()));
			model.addTextValue( myFormat.safeIntToString(pojo.getFilesystemCount()));
			model.addTextValue( myFormat.safeIntToString(pojo.getSnapshotCount()));
			model.addTextValue( myFormat.safeIntToString(pojo.getClonesCount()));
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getCreateDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getCreateDate()));
			model.addTextValue( pojo.getComment() );
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
