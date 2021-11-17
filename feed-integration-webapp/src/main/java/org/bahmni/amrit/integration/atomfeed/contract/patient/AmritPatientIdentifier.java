package org.bahmni.amrit.integration.atomfeed.contract.patient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AmritPatientIdentifier {
    @JsonProperty("MOBILE")
    public String MOBILE;

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    @Override
    public String toString() {
        return "AmritPatientIdentifier{" +
                "MOBILE='" + MOBILE + '\'' +
                '}';
    }
}
