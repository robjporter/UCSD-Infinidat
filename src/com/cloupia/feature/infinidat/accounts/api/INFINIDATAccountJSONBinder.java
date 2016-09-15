package com.cloupia.feature.infinidat.accounts.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.api.INFINIDATAccountJSONBinder;
import com.cloupia.feature.infinidat.accounts.api.INFINIDATJSONBinder;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.service.cIM.inframgr.collector.model.ItemResponse;

@SuppressWarnings("unused")
public class INFINIDATAccountJSONBinder extends INFINIDATJSONBinder {
	private static Logger logger = Logger.getLogger(INFINIDATAccountJSONBinder.class);

	@Override
	public ItemResponse bind(ItemResponse bindable) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountJSONBinder:bind ####----" );}
		String jsonData = bindable.getCollectedData();
		if( jsonData != null && !jsonData.isEmpty()) {}
		return null;
	}
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List<Class> getPersistantClassList() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATAccountJSONBinder:getPersistantClassList ####----" );}
		List<Class> cList = new ArrayList<Class>();
		return cList;
	}
}