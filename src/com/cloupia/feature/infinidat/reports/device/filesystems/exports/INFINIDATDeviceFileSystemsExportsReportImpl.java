package com.cloupia.feature.infinidat.reports.device.filesystems.exports;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATExports;
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
@PersistenceCapable( detachable = "true", table = "infinidat_device_filesystems_export_controller_report")
public class INFINIDATDeviceFileSystemsExportsReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceFileSystemsExportsReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_FILESYSTEM && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceFileSystemsExportsReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATExports> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATExports.class );
		List<INFINIDATExports> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "secret", "secret", true );
		model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "FileSystem Name", "FileSystem Name" );
		model.addTextColumn( "Export Path", "Export Path" );
		model.addTextColumn( "Enabled", "Enabled" );
		model.addTextColumn( "Creation Date", "Creation Date" );
		model.addTextColumn( "Updated Date", "Updated Date" );
		model.addTextColumn( "Comment", "Comment" );
		model.completedHeader();
		
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATExports pojo = objs.get( i );
			model.addTextValue(pojo.getAccountName() +  INFINIDATConstants.REPORT_DATA_SEPARATOR + myFormat.safeIntToString(pojo.getID()));
			model.addTextValue( myFormat.safeIntToString(pojo.getID()));
			model.addTextValue( pojo.getFileSystemName());	
			model.addTextValue(pojo.getExportPath());
			model.addTextValue( myFormat.safeBooleanToString(pojo.getEnabled()));
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getCreateDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getCreateDate()));
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getUpdateDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getUpdateDate()));
			model.addTextValue( pojo.getComment());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
