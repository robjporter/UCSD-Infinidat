package com.cloupia.feature.infinidat.reports.device.filesystems.clones;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATFileSystem;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATVolume;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.format.myFormat;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_device_filesystems_clones_controller_report")
public class INFINIDATDeviceFileSystemsClonesReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceFileSystemsClonesReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### INFINIDATDeviceFileSystemsCloneReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATFileSystem> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATFileSystem.class );
		List<INFINIDATFileSystem> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "secret", "secret", true );
		model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "Name", "Name" );
		model.addTextColumn( "Pool Name", "Pool Name" );
		model.addTextColumn( "Size", "Size" );
		model.addTextColumn( "Security", "Security" );
		model.addTextColumn( "Type", "Type" );
		model.addTextColumn( "Mapped", "Mapped" );
		model.addTextColumn( "Protected", "Protected" );
		model.addTextColumn( "SSD", "SSD" );
		model.addTextColumn( "Creation Date", "Creation Date" );
		model.addTextColumn( "Updated Date", "Updated Date" );
		model.addTextColumn( "Comment", "Comment" );
		model.completedHeader();
		
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATFileSystem pojo = objs.get( i );
			logger.info( "----#### INFINIDATDeviceFileSystemsCloneReportImpl:getTabularReportReport ####---- ID: " + i  + " | TYPE: " + pojo.getDatasetType());
			if( pojo.getType().equals("CLONE")) {
				model.addTextValue(pojo.getAccountName() +  INFINIDATConstants.REPORT_DATA_SEPARATOR + myFormat.safeIntToString(pojo.getID()));
				model.addTextValue( myFormat.safeIntToString(pojo.getID()));
				model.addTextValue( pojo.getName());	
				model.addTextValue( pojo.getPoolName());
				model.addTextValue( myFormat.humanReadableFromLong(pojo.getSize(),true));
				model.addTextValue( pojo.getSecurity());
				model.addTextValue( pojo.getProvType());
				model.addTextValue( myFormat.safeBooleanToString(pojo.getMapped()));
				model.addTextValue( myFormat.safeBooleanToString(pojo.getProtected()));
				model.addTextValue( myFormat.safeBooleanToString(pojo.getSSD()));
				model.addTextValue( myFormat.getDateFromTimestamp(pojo.getCreateDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getCreateDate()));
				model.addTextValue( myFormat.getDateFromTimestamp(pojo.getUpdateDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getUpdateDate()));
				model.addTextValue( pojo.getComment());
				model.completedRow();
			}
		}
		model.updateReport( report );
		return report;
	}
}
