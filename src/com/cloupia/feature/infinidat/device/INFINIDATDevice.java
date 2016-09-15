package com.cloupia.feature.infinidat.device;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATNodes;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("unused")
public class INFINIDATDevice {
	private static Logger logger = Logger.getLogger( INFINIDATDevice.class );
	private static INFINIDATDevice instance = new INFINIDATDevice();
	
	private INFINIDATDevice() {}
	public static INFINIDATDevice getInstance() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevice:INFINIDATDevice ####----" );}
		return instance;
	}
	public boolean connect( String ip, String login, String password ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevice:connect ####----" );}
		return true;
	}
	public String getData( String type ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevice:getData ####----" );}
		return "";
	}
	public static void main( String[] args ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDevice:main ####----" );}
		String jsonData = INFINIDATDevice.getInstance().getData( INFINIDATConstants.CONTROLLERS );
		JsonParser parser = new JsonParser();
		JsonObject obj = (JsonObject) parser.parse(jsonData);
		JsonArray array = obj.getAsJsonArray( "data" );
		List<InventoryDBItemIf> objs = new ArrayList<InventoryDBItemIf>();
		Gson gson = new Gson();
		for (int i=0; i<array.size(); i++) {
			JsonElement ele = array.get(i);
			InventoryDBItemIf invDBObj = gson.fromJson(ele, INFINIDATNodes.class);
			objs.add(invDBObj);
		}
	}
}
