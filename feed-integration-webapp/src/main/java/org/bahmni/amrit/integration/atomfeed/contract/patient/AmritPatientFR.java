package org.bahmni.amrit.integration.atomfeed.contract.patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AmritPatientFR {
    private String healthId;
    private String healthIdNumber;
    private String amritId;
    private AmritPatientProfile profile;

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getHealthIdNumber() {
        return healthIdNumber;
    }

    public void setHealthIdNumber(String healthIdNumber) {
        this.healthIdNumber = healthIdNumber;
    }

    public String getAmritId() {
        return amritId;
    }

    public void setAmritId(String amritId) {
        this.amritId = amritId;
    }

    public AmritPatientProfile getProfile() {
        return profile;
    }

    public void setProfile(AmritPatientProfile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "AmritPatientFR{" +
                "healthId='" + healthId + '\'' +
                ", healthNumber='" + healthIdNumber + '\'' +
                ", amritId='" + amritId + '\'' +
                ", profile=" + profile +
                '}';
    }
}
