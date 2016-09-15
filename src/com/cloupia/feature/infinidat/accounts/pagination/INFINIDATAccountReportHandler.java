package com.cloupia.feature.infinidat.accounts.pagination;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.ColumnDefinition;
import com.cloupia.model.cIM.Query;
import com.cloupia.model.cIM.QueryBuilder;
import com.cloupia.model.cIM.ReportContext;
import com.cloupia.model.cIM.pagination.PaginatedReportHandler;
import com.cloupia.model.cIM.pagination.TabularReportMetadata;
import com.cloupia.service.cIM.inframgr.reportengine.ReportRegistryEntry;

@SuppressWarnings("unused")
public class INFINIDATAccountReportHandler extends PaginatedReportHandler {
	private static Logger logger = Logger.getLogger( INFINIDATAccountReportHandler.class );

	@Override
	public Query appendContextSubQuery( ReportRegistryEntry entry, TabularReportMetadata md, ReportContext rc, Query query ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountReportHandler:appendContextSubQuery ####----" );}
		String contextID = rc.getId();

		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAccountReportHandler:appendContextSubQuery ####---- CONTEXT ID: " + contextID );}
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAccountReportHandler:appendContextSubQuery ####---- CONTEXT: " + rc.toString());}
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAccountReportHandler:appendContextSubQuery ####---- QUERY: " + query );}
		
		
		if( contextID != null && !contextID.isEmpty()) {
			String str[] = contextID.split( ";" );
			String accountName = str[ 0 ];

			if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG1){logger.info( "----#### INFINIDATAccountReportHandler:appendContextSubQuery ####---- ACCOUNT NAME = " + accountName );}
			
			int mgmtColIndex = entry.getManagementColumnIndex();
			ColumnDefinition[] colDefs = md.getColumns();
			ColumnDefinition mgmtCol = colDefs[mgmtColIndex];
			String colId = mgmtCol.getColumnId();
			QueryBuilder sqb = new QueryBuilder();
			sqb.putParam( colId ).eq( accountName );

			if( query == null ) {
				Query q = sqb.get();
				return q;
			} else {
				QueryBuilder qb = new QueryBuilder();
				qb.and(query, sqb.get());
				return qb.get();
			}
		} else {
			/*
			 * UserSession session = UserSessionUtil.getCurrentUserSession(); if
			 * (session == null) { return query; } try {
			 * session.retrieveProfileAndAccess(); if
			 * (session.getAccess().isMSPUser()) { logger.info("MSP User");
			 * Group[] grpList =
			 * GroupManagerImpl.getCustomerGroupsUnderMSP(Integer.parseInt(
			 * session.getLoginProfile().getCustomerId())); QueryBuilder sqb =
			 * new QueryBuilder(); Query [] queries = new Query[grpList.length];
			 * int index = 0; for(Group g : grpList) { queries[index++] = new
			 * QueryBuilder().putParam("groupId").eq( g.getGroupId()).get(); }
			 * query = sqb.or(queries).get(); } } catch(Exception ex) {
			 * logger.error("Error while retrieving group info"); }
			 */

			//logger.info( "################XIOAccountReportHandler:appendContextSubQuery################5" );
			//if( query != null ) { logger.info( "# QUERY = " + query.toString() ); } else { logger.info( "# QUERY = NULL" ); }
			//logger.info( "################XIOAccountReportHandler:appendContextSubQuery################5" );
			
			return query;
		}
	}
}
