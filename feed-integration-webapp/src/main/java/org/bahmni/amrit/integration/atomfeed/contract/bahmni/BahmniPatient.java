package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

import java.util.List;

public class BahmniPatient {
    private BahmniPerson person;
    private List<BahmniPatientIdentifier> identifiers;

    public BahmniPatient(BahmniPerson person, List<BahmniPatientIdentifier> identifiers) {
        this.person = person;
        this.identifiers = identifiers;
    }

    public BahmniPerson getPerson() {
        return person;
    }

    public void setPerson(BahmniPerson person) {
        this.person = person;
    }

    public List<BahmniPatientIdentifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<BahmniPatientIdentifier> identifiers) {
        this.identifiers = identifiers;
    }
}
