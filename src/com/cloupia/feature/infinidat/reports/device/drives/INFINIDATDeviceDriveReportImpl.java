package com.cloupia.feature.infinidat.reports.device.drives;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATServices;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATDrives;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.easyui.annotations.ReportField;
import com.cloupia.model.cIM.DynReportContext;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;
import com.cloupia.service.cIM.inframgr.reports.TabularReportInternalModel;
import com.roporter.feature.format.myFormat;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_device_capability")
public class INFINIDATDeviceDriveReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceDriveReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### INFINIDATDeviceDriveReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATDrives> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATDrives.class );
		List<INFINIDATDrives> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );

		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Node", "Node ID" );
		model.addTextColumn( "Drive ID", "Drive ID" );
		model.addTextColumn( "Model", "Model" );
		model.addTextColumn( "Vendor", "Vendor" );
		model.addTextColumn( "Firmware", "Firmware" );
		model.addTextColumn( "State", "State" );
		model.addTextColumn( "Type", "Type" );
		model.addTextColumn( "Serial Number", "Serial Number" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATDrives pojo = objs.get( i );
			model.addTextValue(myFormat.safeIntToString(pojo.getNodeID()));
			model.addTextValue(myFormat.safeIntToString(pojo.getDriveID()));
			model.addTextValue(pojo.getModel());
			model.addTextValue(pojo.getVendor());
			model.addTextValue(pojo.getFirmware());
			model.addTextValue(pojo.getState());
			model.addTextValue(pojo.getType());
			model.addTextValue(pojo.getSerialNumber());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
