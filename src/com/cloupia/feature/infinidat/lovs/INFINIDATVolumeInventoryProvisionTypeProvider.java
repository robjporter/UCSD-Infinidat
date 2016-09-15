package com.cloupia.feature.infinidat.lovs;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.model.INFINIDATVolume;
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

public class INFINIDATVolumeInventoryProvisionTypeProvider implements LOVProviderIf {
	static Logger logger = Logger.getLogger(INFINIDATVolumeInventoryProvisionTypeProvider.class);
	public static final String NAME = "volume_inventory_provision_type_provider";

	public FormLOVPair[] getLOVs(WizardSession session) {
		FormLOVPair[] lov = null;
		try {
			List<PhysicalInfraAccount> accounts = AccountUtil.getAccountsByType(INFINIDATConstants.INFRA_ACCOUNT_TYPE);
			Map<String,String> lovs = new HashMap<String,String>();
			int i = 0;
			for (PhysicalInfraAccount account : accounts) {
				String accountName = account.getAccountName();
				if (accountName != null && accountName.length() > 0) {
					logger.info("----#### INFINIDATVolumeInventoryProvisionTypeProvider:FormLOVPair ####---- Found account:" + accountName);
					ObjStore<INFINIDATVolume> dummyAssignStore = ObjStoreHelper.getStore(INFINIDATVolume.class);
					List<INFINIDATVolume> objs = dummyAssignStore.query("accountName == '" + accountName + "'");
					logger.info("----#### INFINIDATVolumeInventoryProvisionTypeProvider:FormLOVPair ####---- Objs Size:" + objs.size());
					for (int j = 0; j < objs.size(); j++) {
						INFINIDATVolume volume = objs.get(j);
						logger.info("----#### INFINIDATVolumeInventoryProvisionTypeProvider:FormLOVPair ####---- VOLUME NAME:" + volume.getName());
						logger.info("----#### INFINIDATVolumeInventoryProvisionTypeProvider:FormLOVPair ####---- VOLUME ID:" + volume.getID());
						lovs.put(accountName + ":" + myFormat.safeIntToString(volume.getID()), volume.getName());
					}
				}
			}

            lov = new FormLOVPair[lovs.size()];
			logger.info("----#### INFINIDATVolumeInventoryProvisionTypeProvider:FormLOVPair ####---- VOLUMES FOUND: " + lovs.size());
			
			for (Map.Entry<String, String> entry : lovs.entrySet()) {
			    String key = entry.getKey();
			    Object value = entry.getValue();
			    String[] keys = key.split(":");

				lov[i++] = new FormLOVPair((String)value,keys[1]);
			}

		} catch (Exception ex) {
			logger.info("Exception trying to retrieve FlashArray PhysicalInfraAccount", ex);
		}
		return lov;
	}
}
