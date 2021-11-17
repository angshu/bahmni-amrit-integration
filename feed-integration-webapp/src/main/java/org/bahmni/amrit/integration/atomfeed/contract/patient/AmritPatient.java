package org.bahmni.amrit.integration.atomfeed.contract.patient;

import java.util.List;
import java.util.Map;

public class AmritPatient {
    public String healthId;
    public String healthIdNumber;
    public String name;
    public String gender;
    public String yearOfBirth;
    public String dayOfBirth;
    public String monthOfBirth;
    public List<Map<String,String>> identifiers;
    public AmritPatientAddress address;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public List<Map<String, String>> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Map<String, String>> identifiers) {
        this.identifiers = identifiers;
    }

    public AmritPatientAddress getAddress() {
        return address;
    }

    public void setAddress(AmritPatientAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AmritPatient{" +
                "healthId='" + healthId + '\'' +
                ", healthIdNumber='" + healthIdNumber + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", monthOfBirth='" + monthOfBirth + '\'' +
                ", identifiers=" + identifiers +
                ", address=" + address +
                '}';
    }
}
