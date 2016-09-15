package com.cloupia.feature.infinidat.reports.device;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaEasyReportQueryBuilder;

@SuppressWarnings("unused")
public class INFINIDATQueryBuilder extends CloupiaEasyReportQueryBuilder{
	private static Logger logger = Logger.getLogger( INFINIDATQueryBuilder.class );
	
	@Override
	public String buildQuery( ReportContext context ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATQueryBuilder:buildQuery ####---- CONTEXT: " + context.toString() );}
		
		String[] arr =  null;
		if(context.getId() != null){
			arr = context.getId().split(";");
			return "accountName == '" + arr[ 0 ] + "'";
		}
		return null;
	}
}