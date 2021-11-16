package org.bahmni.amrit.integration.services;

import org.bahmni.amrit.integration.atomfeed.client.ConnectionDetails;
import org.bahmni.amrit.integration.atomfeed.client.WebClientFactory;
import org.bahmni.amrit.integration.atomfeed.contract.encounter.OpenMRSEncounter;
import org.bahmni.amrit.integration.atomfeed.contract.patient.OpenMRSPatient;
import org.bahmni.amrit.integration.atomfeed.contract.patient.OpenMRSPatientFullRepresentation;
import org.bahmni.amrit.integration.atomfeed.mappers.OpenMRSEncounterMapper;
import org.bahmni.amrit.integration.atomfeed.mappers.OpenMRSPatientMapper;
import org.bahmni.webclients.HttpClient;
import org.bahmni.webclients.ObjectMapperRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;

@Component
public class OpenMRSService {

    String patientRestUrl = "/openmrs/ws/rest/v1/patient/";

    public OpenMRSEncounter getEncounter(String encounterUrl) throws IOException {
        HttpClient webClient = WebClientFactory.getClient();
        String urlPrefix = getURLPrefix();

        String encounterJSON = webClient.get(URI.create(urlPrefix + encounterUrl));
        return new OpenMRSEncounterMapper(ObjectMapperRepository.objectMapper).map(encounterJSON);
    }

    public OpenMRSPatient getPatient(String patientUuid) throws IOException, ParseException {
        HttpClient webClient = WebClientFactory.getClient();
        String urlPrefix = getURLPrefix();

        String patientJSON = webClient.get(URI.create(urlPrefix + patientRestUrl + patientUuid+"?v=full"));
        return new OpenMRSPatientMapper().map(patientJSON);
    }

    public OpenMRSPatientFullRepresentation getPatientFR(String patientUrl) throws IOException, ParseException {
        HttpClient webClient = WebClientFactory.getClient();
        String urlPrefix = getURLPrefix();
        String patientJSON = webClient.get(URI.create(urlPrefix + patientUrl));
        return new OpenMRSPatientMapper().mapFullRepresentation(patientJSON);
    }

    private String getURLPrefix() {
        org.bahmni.webclients.ConnectionDetails connectionDetails = ConnectionDetails.get();
        String authenticationURI = connectionDetails.getAuthUrl();

        URL openMRSAuthURL;
        try {
            openMRSAuthURL = new URL(authenticationURI);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Is not a valid URI - " + authenticationURI);
        }
        return String.format("%s://%s", openMRSAuthURL.getProtocol(), openMRSAuthURL.getAuthority());
    }

}
