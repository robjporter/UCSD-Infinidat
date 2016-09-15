package com.cloupia.feature.infinidat.reports.device.snapshots;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATVolume;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.format.myFormat;

@PersistenceCapable( detachable = "true", table = "infinidat_device_volumes_snapshot_controller_report")
public class INFINIDATDeviceVolumeSnapshotReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceVolumeSnapshotReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### INFINIDATDeviceVolumeSnapshotReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATVolume> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATVolume.class );
		List<INFINIDATVolume> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "secret", "secret", true );
		model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "LUN Name", "LUN Name" );
		model.addTextColumn( "Size", "Size" );
		model.addTextColumn( "Pool ID", "Pool ID" );
		model.addTextColumn( "Type", "Type" );
		model.addTextColumn( "Used", "Used" );
		model.addTextColumn( "Mapped", "Mapped" );
		model.addTextColumn( "Protected", "Protected" );
		model.addTextColumn( "SSD", "SSD" );
		model.addTextColumn( "Creation Date", "Creation Date" );
		model.addTextColumn( "Updated Date", "Updated Date" );
		model.addTextColumn( "Comment", "Comment" );
		model.completedHeader();
		
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATVolume pojo = objs.get( i );
			logger.info( "----#### INFINIDATDeviceVolumeSnapshotReportImpl:getTabularReportReport ####---- ID: " + i  + " | TYPE: " + pojo.getVolumeType());
			if( pojo.getVolumeType().equals("SNAP")) {
				model.addTextValue(pojo.getAccountName() +  INFINIDATConstants.REPORT_DATA_SEPARATOR + myFormat.safeIntToString(pojo.getID()));
				model.addTextValue( myFormat.safeIntToString(pojo.getID()));
				model.addTextValue( pojo.getName());
				model.addTextValue( myFormat.humanReadableFromLong(pojo.getSize(),true));	
				model.addTextValue( myFormat.safeIntToString(pojo.getPoolID()));
				model.addTextValue( pojo.getVolumeType());
				model.addTextValue( myFormat.safeLongToString(pojo.getUsed()));
				model.addTextValue( pojo.getMapped().toString());
				model.addTextValue( pojo.getWriteProtected().toString());
				model.addTextValue( pojo.getSSDEnabled().toString());
				model.addTextValue( myFormat.getDateFromTimestamp(pojo.getCreationDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getCreationDate()));
				model.addTextValue( myFormat.getDateFromTimestamp(pojo.getUpdatedDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getUpdatedDate()));
				model.addTextValue( pojo.getComment());
				model.completedRow();
			}
		}
		model.updateReport( report );
		return report;
	}
}
