package com.cloupia.feature.infinidat.reports.device.nodes;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATNodes;
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
@PersistenceCapable( detachable = "true", table = "infinidat_device_controller_report")
public class INFINIDATDeviceNodesReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceNodesReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_NODES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceControllerReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATNodes> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATNodes.class );
		List<INFINIDATNodes> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Node ID", "Node ID" );
		model.addTextColumn( "Name", "Name" );
		model.addTextColumn( "Model", "Model" );
		model.addTextColumn( "Vendor", "Vendor" );
		model.addTextColumn( "Firmware", "Firmware" );
		model.addTextColumn( "State", "State" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATNodes pojo = objs.get( i );
			model.addTextValue( myFormat.safeIntToString(pojo.getNodeID()));
			model.addTextValue( pojo.getName());
			model.addTextValue( pojo.getModel());
			model.addTextValue( pojo.getVendor());
			model.addTextValue( pojo.getFirmware());
			model.addTextValue( pojo.getState());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
