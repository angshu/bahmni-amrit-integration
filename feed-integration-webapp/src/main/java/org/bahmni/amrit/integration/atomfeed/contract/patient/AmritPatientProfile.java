package org.bahmni.amrit.integration.atomfeed.contract.patient;

public class AmritPatientProfile {
    private String hipCode;
    private AmritPatient patient;

    public String getHipCode() {
        return hipCode;
    }

    public void setHipCode(String hipCode) {
        this.hipCode = hipCode;
    }

    public AmritPatient getPatient() {
        return patient;
    }

    public void setPatient(AmritPatient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "AmritPatientProfile{" +
                "hipCode='" + hipCode + '\'' +
                ", patient=" + patient +
                '}';
    }
}
