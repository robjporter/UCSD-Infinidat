package com.cloupia.feature.infinidat.lovs;


import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.forms.wizard.LOVProviderIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;


public class INFINIDATVolumeProvisionTypeProvider implements LOVProviderIf {
    public static final String NAME = "volume_size_provision_type_provider";

    @Override
    public FormLOVPair[] getLOVs(WizardSession session)
    {
        FormLOVPair[] pairs = new FormLOVPair[2];
        pairs[0] = new FormLOVPair("THIN", "THIN");
        pairs[1] = new FormLOVPair("THICK", "THICK");

        return pairs;
    }

}
