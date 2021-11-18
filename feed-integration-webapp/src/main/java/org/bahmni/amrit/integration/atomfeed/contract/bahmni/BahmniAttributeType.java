package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

public class BahmniAttributeType {
    private String uuid;

    public BahmniAttributeType(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
