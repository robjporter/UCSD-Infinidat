package com.cloupia.feature.infinidat.inventory.custom;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.ConvergedStackComponentDetail;

@SuppressWarnings("unused")
public class INFINIDATConvergedStackComponentDetail extends ConvergedStackComponentDetail {
	private static Logger logger = Logger.getLogger( INFINIDATConvergedStackComponentDetail.class );
	private String serialNumber = "";
	private String buildVersion = "0.0.0";
	private String capacity = "";
	private String free = "";
	
	@Override
	public List<String> getComponentSummaryList() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:getComponentSummaryList ####----" );}
		List<String> info = new ArrayList<String>();
		info.add( "Serial Number" + "," + this.getSerialNumber() );
		info.add( "Build" + "," + this.getBuildVersion() );
		info.add( "Free " + "," + this.getFree());
		info.add( "Capacity " + "," + this.getCapacity() );
		return info;
	}
	public String getSerialNumber() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:getSerialNumber ####----" );}
		return this.serialNumber;
	}
	public void setSerialNumber( String serialNumber ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:setSerialNumber ####----" );}
		this.serialNumber = serialNumber;
	}
	public String getBuildVersion() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:getBuildVersion ####----" );}
		return this.buildVersion;
	}
	public void setBuildVersion( String build ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:setBuildVersion ####----" );}
		this.buildVersion = build;
	}
	public String getFree() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:getFree ####----" );}
		return this.free;
	}
	public void setFree(String free) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:setFree ####----" );}
		this.free = free;
	}
	public String getCapacity() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:getCapacity ####----" );}
		return this.capacity;
	}
	public void setCapacity(String capacity) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATConvergedStackComponentDetail:setCapacity ####----" );}
		this.capacity = capacity;
	}
}
