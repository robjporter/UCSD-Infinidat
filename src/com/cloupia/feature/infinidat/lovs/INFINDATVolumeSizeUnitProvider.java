package com.cloupia.feature.infinidat.lovs;


import com.cloupia.model.cIM.FormLOVPair;
import com.cloupia.service.cIM.inframgr.forms.wizard.LOVProviderIf;
import com.cloupia.service.cIM.inframgr.forms.wizard.WizardSession;


public class INFINDATVolumeSizeUnitProvider implements LOVProviderIf
{

    public static final String NAME = "infinidat_volume_size_unit_provider";

    @Override
    public FormLOVPair[] getLOVs(WizardSession session)
    {
        FormLOVPair[] pairs = new FormLOVPair[5];
        pairs[0] = new FormLOVPair("KB", "KB");
        pairs[1] = new FormLOVPair("MB", "MB");
        pairs[2] = new FormLOVPair("GB", "GB");
        pairs[3] = new FormLOVPair("TB", "TB");
        pairs[4] = new FormLOVPair("PB", "PB");

        return pairs;
    }

}
