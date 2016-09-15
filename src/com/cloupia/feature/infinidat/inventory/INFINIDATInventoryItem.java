package com.cloupia.feature.infinidat.inventory;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemDataObjectBinderIf;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemParserIf;
import com.cloupia.service.cIM.inframgr.collector.controller.MappedItemListener;
import com.cloupia.service.cIM.inframgr.collector.model.ItemDataFormat;
import com.cloupia.service.cIM.inframgr.collector.model.ItemIf;

@SuppressWarnings({"rawtypes","unused"})
public class INFINIDATInventoryItem implements ItemIf {
	private static Logger logger = Logger.getLogger( INFINIDATInventoryItem.class );
	private String type;
	private Class model;

	public INFINIDATInventoryItem( String type, Class model ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:INFINIDATInventoryItem ####----" );}
		this.type = type;
		this.model = model;
	}
	@Override
	public ItemDataObjectBinderIf getBinder() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:ItemDataObjectBinderIf ####----" );}
		return null;
	}
	@Override
	public Class getBoundToClass() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:getBoundToClass ####----" );}
		return model;
	}
	@Override
	public ItemDataFormat getCollectedDataFormat() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:getCollectedDataFormat ####----" );}
		return null;
	}
	@Override
	public MappedItemListener getItemListener() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:getItemListener ####----" );}
		return null;
	}
	@Override
	public String getLabel() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:getLabel ####----" );}
		return this.type;
	}
	@Override
	public String getName() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:getName ####----" );}
		return this.type;
	}
	@Override
	public ItemDataFormat getParsedDataFormat() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:getParsedDataFormat ####----" );}
		return null;
	}
	@Override
	public ItemParserIf getParser() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATInventoryItem:getParser ####----" );}
		return null;
	}
}
