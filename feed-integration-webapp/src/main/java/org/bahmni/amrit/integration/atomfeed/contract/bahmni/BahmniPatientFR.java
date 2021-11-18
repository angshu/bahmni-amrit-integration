package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

import java.util.List;

public class BahmniPatientFR {
    private BahmniPatient patient;
    private List<Object> relationships;

    public BahmniPatientFR(BahmniPatient patient, List<Object> relationships) {
        this.patient = patient;
        this.relationships = relationships;
    }

    public BahmniPatient getPatient() {
        return patient;
    }

    public void setPatient(BahmniPatient patient) {
        this.patient = patient;
    }

    public List<Object> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Object> relationships) {
        this.relationships = relationships;
    }
}
