package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;

import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@PersistenceCapable( detachable = "true", table = "infinidat_hba" )
public class INFINIDATHba implements InventoryDBItemIf, ReportableIf {

	@Override
	public String getInstanceQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccountName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAccountName(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
