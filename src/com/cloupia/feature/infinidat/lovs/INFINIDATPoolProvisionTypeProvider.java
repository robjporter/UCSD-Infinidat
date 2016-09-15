package com.cloupia.feature.infinidat.lovs;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATPools;
import com.cloupia.fw.objstore.ObjStore;
import com.cloupia.fw.objstore.ObjStoreHelper;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.forms.wizard.LOVProviderIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.roporter.feature.format.myFormat;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class INFINIDATPoolProvisionTypeProvider implements LOVProviderIf {
	static Logger logger = Logger.getLogger(INFINIDATPoolProvisionTypeProvider.class);
    public static final String NAME = "volume_pool_provision_type_provider";
    
    public FormLOVPair[] getLOVs(WizardSession session) {
    	FormLOVPair[] lov = null;
        try {
        	List<PhysicalInfraAccount> accounts = AccountUtil.getAccountsByType(INFINIDATConstants.INFRA_ACCOUNT_TYPE);
        	Map<String,String> lovs = new HashMap<String,String>();
        	int i = 0;
        	
            for (PhysicalInfraAccount account:accounts) {
            	String accountName = account.getAccountName();
                if (accountName != null && accountName.length() > 0) {
                	logger.info("----#### INFINIDATPoolProvisionTypeProvider:FormLOVPair ####---- Found account:" + accountName);
                	ObjStore<INFINIDATPools> dummyAssignStore = ObjStoreHelper.getStore( INFINIDATPools.class );
                	List<INFINIDATPools> objs = dummyAssignStore.query( "accountName == '" + accountName + "'" );
                    logger.info("----#### INFINIDATPoolProvisionTypeProvider:FormLOVPair ####---- Objs Size:" + objs.size());
                	for( int j = 0; j < objs.size(); j++ ) {
                		INFINIDATPools pool = objs.get(j);
                        logger.info("----#### INFINIDATPoolProvisionTypeProvider:FormLOVPair ####---- POOL NAME:" + pool.getName());
                        logger.info("----#### INFINIDATPoolProvisionTypeProvider:FormLOVPair ####---- POOL ID:" + pool.getPoolID());
                        lovs.put(accountName + ":" + myFormat.safeIntToString(pool.getPoolID()), pool.getName());
                	}
                }
            }
            lov = new FormLOVPair[lovs.size()];
    		logger.info("----#### INFINIDATPoolProvisionTypeProvider:FormLOVPair ####---- POOLS FOUND: " + lovs.size());
    		
    		for (Map.Entry<String, String> entry : lovs.entrySet()) {
    		    String key = entry.getKey();
    		    Object value = entry.getValue();
    		    String[] keys = key.split(":");

    			lov[i++] = new FormLOVPair((String)value,keys[1]);
    		}
        }
        catch (Exception ex) {
        	logger.info("Exception trying to retrieve FlashArray PhysicalInfraAccount", ex);
        }
        return lov;
    }
}
