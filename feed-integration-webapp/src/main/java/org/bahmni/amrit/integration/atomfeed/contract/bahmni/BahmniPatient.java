package org.bahmni.amrit.integration.atomfeed.contract.bahmni;

import java.util.List;

public class BahmniPatient {
    private BahmniPerson person;
    private List<Object> identifiers;

    public BahmniPatient(BahmniPerson person, List<Object> identifiers) {
        this.person = person;
        this.identifiers = identifiers;
    }

    public BahmniPerson getPerson() {
        return person;
    }

    public void setPerson(BahmniPerson person) {
        this.person = person;
    }

    public List<Object> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Object> identifiers) {
        this.identifiers = identifiers;
    }
}
