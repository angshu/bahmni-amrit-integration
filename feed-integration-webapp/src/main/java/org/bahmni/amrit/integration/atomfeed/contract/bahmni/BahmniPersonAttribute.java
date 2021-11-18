package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

public class BahmniPersonAttribute {
    private BahmniAttributeType attributeType;
    private String value;

    public BahmniPersonAttribute(BahmniAttributeType attributeType, String value) {
        this.attributeType = attributeType;
        this.value = value;
    }

    public BahmniAttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(BahmniAttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
