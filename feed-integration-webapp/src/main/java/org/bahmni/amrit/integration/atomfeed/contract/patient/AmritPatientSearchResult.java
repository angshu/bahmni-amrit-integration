package org.bahmni.amrit.integration.atomfeed.contract.patient;

import java.util.List;

public class AmritPatientSearchResult {
    private String next;
    private List<AmritPatientFR> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<AmritPatientFR> getData() {
        return data;
    }

    public void setData(List<AmritPatientFR> data) {
        this.data = data;
    }
}
