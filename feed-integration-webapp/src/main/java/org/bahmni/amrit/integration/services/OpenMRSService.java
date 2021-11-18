package org.bahmni.amrit.integration.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bahmni.amrit.integration.atomfeed.client.AtomFeedProperties;
import org.bahmni.amrit.integration.atomfeed.client.BahmniWebClientFactory;
import org.bahmni.amrit.integration.atomfeed.client.ConnectionDetails;
import org.bahmni.amrit.integration.atomfeed.contract.bahmni.BahmniPatientFR;
import org.bahmni.amrit.integration.atomfeed.contract.encounter.OpenMRSEncounter;
import org.bahmni.amrit.integration.atomfeed.contract.patient.AmritPatientFR;
import org.bahmni.amrit.integration.atomfeed.contract.patient.OpenMRSPatient;
import org.bahmni.amrit.integration.atomfeed.contract.patient.OpenMRSPatientFullRepresentation;
import org.bahmni.amrit.integration.atomfeed.mappers.OpenMRSEncounterMapper;
import org.bahmni.amrit.integration.atomfeed.mappers.OpenMRSPatientMapper;
import org.bahmni.webclients.HttpClient;
import org.bahmni.webclients.ObjectMapperRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;

import static org.bahmni.webclients.ObjectMapperRepository.objectMapper;

@Component
public class OpenMRSService {

    String patientRestUrl = "/openmrs/ws/rest/v1/patient/";

    public OpenMRSEncounter getEncounter(String encounterUrl) throws IOException {
        HttpClient webClient = BahmniWebClientFactory.getClient();
        String urlPrefix = getURLPrefix();

        String encounterJSON = webClient.get(URI.create(urlPrefix + encounterUrl));
        return new OpenMRSEncounterMapper(ObjectMapperRepository.objectMapper).map(encounterJSON);
    }

    public OpenMRSPatient getPatient(String patientUuid) throws IOException, ParseException {
        HttpClient webClient = BahmniWebClientFactory.getClient();
        String urlPrefix = getURLPrefix();

        String patientJSON = webClient.get(URI.create(urlPrefix + patientRestUrl + patientUuid+"?v=full"));
        return new OpenMRSPatientMapper().map(patientJSON);
    }

    public OpenMRSPatientFullRepresentation getPatientFR(String patientUrl) throws IOException, ParseException {
        HttpClient webClient = BahmniWebClientFactory.getClient();
        String urlPrefix = getURLPrefix();
        String patientJSON = webClient.get(URI.create(urlPrefix + patientUrl));
        return new OpenMRSPatientMapper().mapFullRepresentation(patientJSON);
    }

    public boolean isPatientExists(String patientRestUrl, String amritIdentifier, String amritIdentifierTypeUuid) throws IOException {
        HttpClient webClient = BahmniWebClientFactory.getClient();
        String patientJSON = webClient.get(URI.create(patientRestUrl));

        JsonNode jsonNode = objectMapper.readTree(patientJSON);
        if (jsonNode.path("results").size() == 0) return false;
        JsonNode identifiers = jsonNode.path("results").get(0).path("identifiers");
        for (JsonNode identifier : identifiers) {
            String patientIdentifier = identifier.path("identifier").asText();
            String identifierTypeUuid = identifier.path("identifierType").path("uuid").asText();
            System.out.println(patientIdentifier + "" + identifierTypeUuid);
            if (patientIdentifier.equals(amritIdentifier) && identifierTypeUuid.equals(amritIdentifierTypeUuid)) {
                return true;
            }
        }
        return false;
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

    public void createOpenMRSPatient(AmritPatientFR patient) throws JsonProcessingException {
        // Amrit => Bahmni
        BahmniPatientFR newPatient = new OpenMRSPatientMapper().map(patient);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String patientData = ow.writeValueAsString(newPatient);
        System.out.println(patientData);
        // call bahmni core api with new patient data
        try {
            AtomFeedProperties properties = AtomFeedProperties.getInstance();
            String result = createPatientInBahmni(properties, patientData);
            System.out.println(result);
        } catch (IOException | AuthenticationException e) {
            e.printStackTrace();
        }
    }

    private String createPatientInBahmni(AtomFeedProperties properties, String patientData) throws UnsupportedEncodingException, AuthenticationException {
        String result = "";
        HttpPost post = new HttpPost(properties.getProperty("bahmnicore.patient.profile"));
        post.addHeader("content-type", "application/json");
        post.setEntity(new StringEntity(patientData));

        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(properties.getProperty("openmrs.user"), properties.getProperty("openmrs.password"));
        post.addHeader(new BasicScheme().authenticate(credentials, post, null));

        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(response.getStatusLine().getStatusCode());
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
