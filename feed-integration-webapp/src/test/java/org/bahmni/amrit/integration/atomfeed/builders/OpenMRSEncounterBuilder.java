package org.bahmni.amrit.integration.atomfeed.builders;

import org.bahmni.amrit.integration.atomfeed.contract.encounter.OpenMRSEncounter;
import org.bahmni.amrit.integration.atomfeed.contract.encounter.OpenMRSOrder;
import org.bahmni.amrit.integration.atomfeed.contract.encounter.OpenMRSProvider;

import java.util.Arrays;

public class OpenMRSEncounterBuilder {
    private OpenMRSEncounter openMRSEncounter;

    public OpenMRSEncounterBuilder() {
        openMRSEncounter = new OpenMRSEncounter();
    }

    public OpenMRSEncounterBuilder withEncounterUuid(String encounterUuid) {
        openMRSEncounter.setEncounterUuid(encounterUuid);
        return this;
    }

    public OpenMRSEncounterBuilder withPatientUuid(String patientUuid) {
        openMRSEncounter.setPatientUuid(patientUuid);
        return this;
    }

    public OpenMRSEncounterBuilder withTestOrder(OpenMRSOrder order) {
        openMRSEncounter.addTestOrder(order);
        return this;
    }

    public OpenMRSEncounterBuilder withProvider(OpenMRSProvider openMRSProvider){
        openMRSEncounter.setProviders(Arrays.asList(openMRSProvider));
        return this;
    }
    public OpenMRSEncounter build() {
        return openMRSEncounter;
    }
}
