package com.cloupia.feature.infinidat.lovs;

import org.apache.log4j.Logger;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.TabularReport;
import com.cloupia.service.cIM.inframgr.TabularReportGeneratorIf;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;

public class INFINIDATExportPermissions implements TabularReportGeneratorIf {
	private static Logger logger = Logger.getLogger( INFINIDATExportPermissions.class );
	public static final String EXPORT_TABULAR_PROVIDER = "infinidat_export_permissions_tabular_provider";
	
	@Override
	public TabularReport getTabularReportReport(ReportRegistryEntry reportEntry, ReportContext context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
