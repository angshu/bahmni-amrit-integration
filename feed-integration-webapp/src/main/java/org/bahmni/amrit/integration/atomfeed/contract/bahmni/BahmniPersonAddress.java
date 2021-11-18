package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

public class BahmniPersonAddress {
    private String address1;
    private String countyDistrict;
    private String stateProvince;

    public BahmniPersonAddress(String address1, String countyDistrict, String stateProvince) {
        this.address1 = address1;
        this.countyDistrict = countyDistrict;
        this.stateProvince = stateProvince;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCountyDistrict() {
        return countyDistrict;
    }

    public void setCountyDistrict(String countyDistrict) {
        this.countyDistrict = countyDistrict;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
}
