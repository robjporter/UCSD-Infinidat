package com.cloupia.feature.infinidat.reports.device.services;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATServices;
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
import com.roporter.feature.json.myJson;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_device_services")
public class INFINIDATDeviceServicesReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceServicesReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_SERVICES && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceCapabilityReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );
		
		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATServices> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATServices.class );
		List<INFINIDATServices> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "Node ID", "Node ID" );
		model.addTextColumn( "Role", "Role" );
		model.addTextColumn( "Feature Name", "Feature Name" );
		model.addTextColumn( "Status", "Status" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATServices pojo = objs.get( i );
			model.addTextValue( myFormat.safeIntToString(pojo.getNodeID()));
			model.addTextValue(pojo.getRole());
			model.addTextValue( pojo.getFeature());
			model.addTextValue( myFormat.safeBooleanToString(pojo.getStatus()));
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
