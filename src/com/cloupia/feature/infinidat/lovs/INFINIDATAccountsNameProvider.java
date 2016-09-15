package com.cloupia.feature.infinidat.lovs;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.lib.connector.account.AccountUtil;
import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.forms.wizard.LOVProviderIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;
import com.cloupia.lib.connector.account.PhysicalInfraAccount;

import java.util.List;

import org.apache.log4j.Logger;

public class INFINIDATAccountsNameProvider implements LOVProviderIf {
	static Logger logger = Logger.getLogger(INFINIDATAccountsNameProvider.class);
    public static final String NAME = "INFINIDATAccountsNameProvider";
    
    public FormLOVPair[] getLOVs(WizardSession session) {
          FormLOVPair[] lov = null;
          try
          {
              List<PhysicalInfraAccount> accounts = AccountUtil.getAccountsByType(INFINIDATConstants.INFRA_ACCOUNT_TYPE);
              lov = new FormLOVPair[accounts.size()];
              int i = 0;
              for (PhysicalInfraAccount account:accounts)
              {
            	  
                  String accountName = account.getAccountName();
                  logger.info("Found account:" + accountName);
                  lov[i++] = new FormLOVPair(accountName,accountName);
              }
          }
          catch (Exception ex)
          {
              logger.info("Exception trying to retrieve FlashArray PhysicalInfraAccount", ex);
          }
          return lov;
       }
    }
