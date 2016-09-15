package com.cloupia.feature.infinidat.reports.device.hosts;

import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATHosts;
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
@PersistenceCapable( detachable = "true", table = "infinidat_device_host_controller_report")
public class INFINIDATDeviceHostReportImpl implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceHostReportImpl.class );

	@Override
	public TabularReport getTabularReportReport( ReportRegistryEntry reportEntry, ReportContext context ) throws Exception {
		if(INFINIDATConstants.DEBUG_HOSTS && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceHostReportImpl:getTabularReportReport ####----" );}
		TabularReport report = new TabularReport();
		report.setGeneratedTime( System.currentTimeMillis());
		report.setReportName( reportEntry.getReportLabel());
		report.setContext( context );

		String accName = context.getId().split(";")[0];
		ObjStore<INFINIDATHosts> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATHosts.class );
		List<INFINIDATHosts> objs = dummyAssignStore.query( "accountName == '" + accName + "'" );
		
		TabularReportInternalModel model = new TabularReportInternalModel();
		model.addTextColumn( "secret", "secret", true);
		model.addTextColumn( "Global ID", "Global ID" );
		model.addTextColumn( "Host Protocol", "Host Protocol" );
		model.addTextColumn( "Host Name", "Host Name" );
		model.addTextColumn( "Cluster Name", "Cluster Name" );
		model.addTextColumn( "Port Count", "Port Count" );
		model.addTextColumn( "Lun Count", "Lun Count" );
		model.addTextColumn( "Created Date", "Created Date" );
		model.addTextColumn( "Updated Date", "Updated Date" );
		model.addTextColumn( "Comment", "Comment" );
		model.completedHeader();
		for (int i = 0; i < objs.size(); i++) {
			INFINIDATHosts pojo = objs.get( i );
			model.addTextValue(pojo.getAccountName() + INFINIDATConstants.REPORT_DATA_SEPARATOR + myFormat.safeIntToString(pojo.getID()));
			model.addTextValue( Integer.toString(pojo.getID()));
			model.addTextValue( pojo.getType());
			model.addTextValue( pojo.getName());
			model.addTextValue( pojo.getClusterName());
			model.addTextValue( Integer.toString(pojo.getPortCount()));
			model.addTextValue( Integer.toString(pojo.getLunCount()));
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getCreatedDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getCreatedDate()));
			model.addTextValue( myFormat.getDateFromTimestamp(pojo.getUpdatedDate()) + " " + myFormat.getTimeFromTimestamp(pojo.getUpdatedDate()));
			model.addTextValue( pojo.getComment());
			model.completedRow();
		}
		model.updateReport( report );
		return report;
	}
}
