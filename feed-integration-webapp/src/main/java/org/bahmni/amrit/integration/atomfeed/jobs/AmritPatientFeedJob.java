package org.bahmni.amrit.integration.atomfeed.jobs;

import org.bahmni.amrit.integration.atomfeed.client.AtomFeedProperties;
import org.bahmni.amrit.integration.atomfeed.contract.patient.AmritPatientFR;
import org.bahmni.amrit.integration.atomfeed.contract.patient.AmritPatientSearchResult;
import org.bahmni.amrit.integration.services.OpenMRSService;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.io.IOException;

@DisallowConcurrentExecution
@Component("amritPatientFeedJob")
@ConditionalOnExpression("'${enable.scheduling}'=='true'")
public class AmritPatientFeedJob implements FeedJob {
    private final Logger logger = LoggerFactory.getLogger(AmritPatientFeedJob.class);
    private final AmritHttpClient amritHttpClient;
    private final OpenMRSService openMRSService;

    @Autowired
    public AmritPatientFeedJob(AmritHttpClient amritHttpClient, OpenMRSService openMRSService) {
        this.amritHttpClient = amritHttpClient;
        this.openMRSService = openMRSService;
    }

    public void process() throws IOException {
        logger.info("Processing amrit patients feed...");
        AmritPatientSearchResult patients = amritHttpClient.getPatients(null);
        for (AmritPatientFR patient : patients.getData()) {
            AtomFeedProperties properties = AtomFeedProperties.getInstance();

            final String amritId = patient.getAmritId();
            final String amritIdentifierTypeUuid = properties.getProperty("bahmni.amrit.identifierType.uuid");
            String patientUri = String.format(properties.getProperty("bahmni.patient.search.uri"), amritId);
            System.out.println(patientUri);

            boolean patientExists = openMRSService.isPatientExists(patientUri, amritId, amritIdentifierTypeUuid);
            System.out.println(patientExists);
        }
        logger.info("Completed processing feed...");
    }
}
