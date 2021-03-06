package com.cloupia.feature.infinidat.reports.device.fcnetwork;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATFCNetwork;
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
@PersistenceCapable( detachable = "true", table = "infinidat_fc_interface")
public class INFINIDATDeviceFCInterfaceReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceFCInterfaceReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### INFINIDATDeviceFCInterfaceReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATFCNetwork> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATFCNetwork.class );
		List<INFINIDATFCNetwork> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Node ID", "Node ID" );
		model.addTextColumn( "Port No.", "Port No." );
		model.addTextColumn( "Vendor", "Vendor" );
		model.addTextColumn( "Role", "Role" );
		model.addTextColumn( "WWNN", "WWNN" );
		model.addTextColumn( "WWPN", "WWPN" );
		model.addTextColumn( "Speed", "Speed" );
		model.addTextColumn( "Link", "Link" );
		model.addTextColumn( "Enabled", "Enabled" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATFCNetwork pojo = objs.get( i );
			logger.info( "----#### INFINIDATDeviceEInterfaceReportImpl:getTabularReportReport ####---- DEBUG FC: " + pojo.debug());
			model.addTextValue( myFormat.safeIntToString(pojo.getNodeID()));
			model.addTextValue( myFormat.safeIntToString(pojo.getHBAID()));
			model.addTextValue( pojo.getVendor());
			model.addTextValue( pojo.getRole());
			model.addTextValue( pojo.getWWNN());
			model.addTextValue( pojo.getWWPN());
			model.addTextValue( myFormat.humanReadableFromLong(pojo.getSpeed(),true));
			model.addTextValue( pojo.getLinkState());
			model.addTextValue( myFormat.safeBooleanToString(pojo.getEnabled()));
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
