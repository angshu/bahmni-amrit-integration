package org.bahmni.amrit.integration.atomfeed.jobs;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bahmni.amrit.integration.atomfeed.client.AtomFeedProperties;
import org.bahmni.amrit.integration.atomfeed.contract.patient.AmritPatientSearchResult;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.bahmni.webclients.ObjectMapperRepository.objectMapper;

@Component
public class AmritHttpClient {

    public AmritPatientSearchResult getPatients(String url) {
        AtomFeedProperties properties = AtomFeedProperties.getInstance();
        String amritServerUrl = properties.getProperty("amrit.patient.uri");
        if (url != null && !url.isEmpty()) {
            amritServerUrl = url;
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(amritServerUrl);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    AmritPatientSearchResult results = objectMapper.readValue(result, AmritPatientSearchResult.class);
                    return results;
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AmritPatientSearchResult();
    }

}
