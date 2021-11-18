package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BahmniPatientIdentifier {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String identifierSourceUuid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String identifierPrefix;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String identifierType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String identifier;

    public BahmniPatientIdentifier(String identifierSourceUuid, String identifierPrefix, String identifierType, String identifier) {
        this.identifierSourceUuid = identifierSourceUuid;
        this.identifierPrefix = identifierPrefix;
        this.identifierType = identifierType;
        this.identifier = identifier;
    }

    public BahmniPatientIdentifier(String identifierType, String identifier) {
        this.identifierType = identifierType;
        this.identifier = identifier;
    }
}
