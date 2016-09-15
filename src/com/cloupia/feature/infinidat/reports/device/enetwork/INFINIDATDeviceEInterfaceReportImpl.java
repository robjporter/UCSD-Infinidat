package com.cloupia.feature.infinidat.reports.device.enetwork;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.inventory.model.INFINIDATEthNetwork;
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
@PersistenceCapable( detachable = "true", table = "infinidat_e_interface")
public class INFINIDATDeviceEInterfaceReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceEInterfaceReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### INFINIDATDeviceEInterfaceReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATEthNetwork> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATEthNetwork.class );
		List<INFINIDATEthNetwork> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Node ID", "Node ID" );
		//model.addTextColumn( "ID", "ID" );
		model.addTextColumn( "Port Number", "Port Number" );
		model.addTextColumn( "Name", "Name" );
		model.addTextColumn( "Role", "Role" );
		model.addTextColumn( "Model", "Model" );
		model.addTextColumn( "Vendor", "Vendor" );
		model.addTextColumn( "Firmware", "Firmware" );
		model.addTextColumn( "State", "State" );
		model.addTextColumn( "HW Address", "HW Address" );
		model.addTextColumn( "IPv4 Address", "IPv4 Address" );
		model.addTextColumn( "Speed", "Speed" );
		model.addTextColumn( "Link State", "Link State" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATEthNetwork pojo = objs.get( i );
			if(!pojo.getRole().trim().equals("Internal") && !pojo.getRole().trim().equals("iDrac")){
				model.addTextValue( myFormat.safeIntToString(pojo.getNodeID()));
				model.addTextValue( myFormat.safeIntToString(pojo.getPortID()));
				model.addTextValue(pojo.getName());
				model.addTextValue(pojo.getRole());
				model.addTextValue(pojo.getModel());
				model.addTextValue(pojo.getVendor());
				model.addTextValue(pojo.getFirmware());
				model.addTextValue(pojo.getState());
				model.addTextValue(pojo.getHWAddr());
				model.addTextValue(pojo.getIPv4());
				model.addTextValue(myFormat.humanReadableFromLong(pojo.getSpeed(),true));
				model.addTextValue(pojo.getLinkState());
				model.completedRow();
			}
		}
		model.updateReport( report );
		return report;
	}
}
