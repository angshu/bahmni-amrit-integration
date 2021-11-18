package org.bahmni.amrit.integration.atomfeed.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bahmni.amrit.integration.atomfeed.client.AtomFeedProperties;
import org.bahmni.amrit.integration.atomfeed.contract.bahmni.*;
import org.bahmni.amrit.integration.atomfeed.contract.patient.*;
import org.bahmni.webclients.ObjectMapperRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OpenMRSPatientMapper {
    private ObjectMapper objectMapper;
    private SimpleDateFormat dateOfBirthFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public OpenMRSPatientMapper() {
        this.objectMapper = ObjectMapperRepository.objectMapper;
    }

    public OpenMRSPatient map(String patientJSON) throws IOException, ParseException {
        OpenMRSPatient patient = new OpenMRSPatient();
        JsonNode jsonNode = objectMapper.readTree(patientJSON);

        patient.setPatientId(jsonNode.path("identifiers").get(0).path("identifier").asText());
        patient.setGivenName(jsonNode.path("person").path("preferredName").path("givenName").asText().replaceAll("[\\W&&[^-]]", " "));
        patient.setFamilyName(jsonNode.path("person").path("preferredName").path("familyName").asText().replaceAll("[\\W&&[^-]]", " "));
        patient.setMiddleName(jsonNode.path("person").path("preferredName").path("middleName").asText().replaceAll("[\\W&&[^-]]", " "));
        patient.setGender(jsonNode.path("person").path("gender").asText());
        patient.setBirthDate(dateOfBirthFormat.parse(jsonNode.path("person").path("birthdate").asText()));

        return patient;
    }

    public OpenMRSPatientFullRepresentation mapFullRepresentation(String patientJSON) throws IOException {
        return objectMapper.readValue(patientJSON, OpenMRSPatientFullRepresentation.class);
    }

    public BahmniPatientFR map(AmritPatientFR patient) {
        AtomFeedProperties properties = AtomFeedProperties.getInstance();

        AmritPatient amritPatient = patient.getProfile().getPatient();

        BahmniPersonName personName = getBahmniPersonName(amritPatient);
        BahmniPersonAddress personAddress = getBahmniPersonAddress(amritPatient);
        BahmniPatientIdentifier bahmniIdentifier = getBahmniIdentifier(properties);
        BahmniPatientIdentifier amritIdentifier = new BahmniPatientIdentifier(properties.getProperty("bahmni.amrit.identifierType.uuid"), patient.getAmritId());
        BahmniPersonAttribute secondaryContactAttribute = getSecondaryContactAttribute(properties, amritPatient);
        String birthDate = getBirthDate(amritPatient);


        BahmniPerson bahmniPerson = new BahmniPerson(Collections.singletonList(personName), Collections.singletonList(personAddress), birthDate, amritPatient.gender, Collections.singletonList(secondaryContactAttribute));
        BahmniPatient bahmniPatient = new BahmniPatient(bahmniPerson, new ArrayList<>(Arrays.asList(bahmniIdentifier, amritIdentifier)));
        return new BahmniPatientFR(bahmniPatient, new ArrayList<>());
    }

    private BahmniPersonAttribute getSecondaryContactAttribute(AtomFeedProperties properties, AmritPatient amritPatient) {
        String secondaryContactAttributeUuid = properties.getProperty("bahmni.attribute.secondaryContact.uuid");
        BahmniAttributeType bahmniSecondaryContactAttributeType = new BahmniAttributeType(secondaryContactAttributeUuid);
        return new BahmniPersonAttribute(bahmniSecondaryContactAttributeType, getMobileNumber(amritPatient));
    }

    private BahmniPatientIdentifier getBahmniIdentifier(AtomFeedProperties properties) {
        String sourceUuid = properties.getProperty("bahmni.identifierSource.uuid");
        String prefix = properties.getProperty("bahmni.identifier.prefix");
        String type = properties.getProperty("bahmni.identifierType.uuid");
        return new BahmniPatientIdentifier(sourceUuid, prefix, type, null);
    }

    private String getBirthDate(AmritPatient amritPatient) {
        Calendar calendar = Calendar.getInstance();
        int yearOfBirth = Integer.parseInt(amritPatient.yearOfBirth);
        int monthOfBirth = Integer.parseInt(amritPatient.monthOfBirth);
        int dayOfBirth = Integer.parseInt(amritPatient.getDayOfBirth());
        calendar.set(yearOfBirth, monthOfBirth - 1, dayOfBirth, 0, 0);
        return calendar.getTime().toInstant().toString();
    }

    private BahmniPersonAddress getBahmniPersonAddress(AmritPatient amritPatient) {
        AmritPatientAddress amritPatientAddress = amritPatient.getAddress();
        return new BahmniPersonAddress(amritPatientAddress.getLine(), amritPatientAddress.getDistrict(), amritPatientAddress.getState());
    }

    private BahmniPersonName getBahmniPersonName(AmritPatient amritPatient) {
        //more than 3 words in a name may require parse it differently
        String[] name = amritPatient.getName().split(" ");
        if (name.length == 1) return new BahmniPersonName(name[0], null, ".");
        if (name.length == 2) return new BahmniPersonName(name[0], null, name[1]);
        return new BahmniPersonName(name[0], name[1], name[2]);
    }

    private String getMobileNumber(AmritPatient amritPatient) {
        List<Map<String, String>> amritPatientIdentifiers = amritPatient.getIdentifiers();
        for (Map<String, String> identifier : amritPatientIdentifiers) {
            if (identifier.containsKey("MOBILE")) {
                return identifier.get("MOBILE");
            }
        }
        return "";
    }
}
