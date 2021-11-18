package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

import java.util.List;

public class BahmniPerson {
    private List<BahmniPersonName> names;
    private List<BahmniPersonAddress> addresses;
    private String birthdate;
    private String gender;
    private List<BahmniPersonAttribute> attributes;

    public BahmniPerson(List<BahmniPersonName> names, List<BahmniPersonAddress> addresses, String birthdate, String gender, List<BahmniPersonAttribute> attributes) {
        this.names = names;
        this.addresses = addresses;
        this.birthdate = birthdate;
        this.gender = gender;
        this.attributes = attributes;
    }

    public List<BahmniPersonName> getNames() {
        return names;
    }

    public void setNames(List<BahmniPersonName> names) {
        this.names = names;
    }

    public List<BahmniPersonAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<BahmniPersonAddress> addresses) {
        this.addresses = addresses;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<BahmniPersonAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<BahmniPersonAttribute> attributes) {
        this.attributes = attributes;
    }
}
