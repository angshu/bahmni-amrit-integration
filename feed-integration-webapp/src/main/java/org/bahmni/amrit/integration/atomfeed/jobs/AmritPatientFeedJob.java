package org.bahmni.amrit.integration.atomfeed.jobs;

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

    @Autowired
    public AmritPatientFeedJob(AmritHttpClient amritHttpClient) {
        this.amritHttpClient = amritHttpClient;
    }

    public void process() throws InterruptedException, IOException {
        logger.info("Processing amrit patients feed...");
        amritHttpClient.getPatients();
        logger.info("Completed processing feed...");
    }
}
