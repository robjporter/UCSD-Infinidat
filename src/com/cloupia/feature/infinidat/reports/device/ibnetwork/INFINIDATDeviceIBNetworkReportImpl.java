package com.cloupia.feature.infinidat.reports.device.ibnetwork;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATHosts;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATIBNetwork;
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
public class INFINIDATDeviceIBNetworkReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceIBNetworkReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		logger.info( "----#### INFINIDATDeviceIBNetworkReportImpl:getTabularReportReport ####----" );
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATIBNetwork> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATIBNetwork.class );
		List<INFINIDATIBNetwork> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Node ID", "Node ID" );
		model.addTextColumn( "Interface ID", "Interface ID" );
		model.addTextColumn( "Model", "Model" );
		model.addTextColumn( "Vendor", "Vendor" );
		model.addTextColumn( "Firmware", "Firmware" );
		model.addTextColumn( "State", "State" );
		model.addTextColumn( "State Description", "State Description" );
		model.addTextColumn( "Comment", "Comment" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATIBNetwork pojo = objs.get( i );
			model.addTextValue( myFormat.safeIntToString(pojo.getNodeID()));
			model.addTextValue( myFormat.safeIntToString(pojo.getID()));
			model.addTextValue( pojo.getModel() );
			model.addTextValue( pojo.getVendor() );
			model.addTextValue( pojo.getFirmware() );
			model.addTextValue( pojo.getState() );
			model.addTextValue( pojo.getStateDescription() );
			model.addTextValue( pojo.getComment() );
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
