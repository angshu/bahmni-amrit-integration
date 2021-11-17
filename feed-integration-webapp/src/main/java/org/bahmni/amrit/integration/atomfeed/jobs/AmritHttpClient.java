package org.bahmni.amrit.integration.atomfeed.jobs;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
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

    public void getPatients() throws IOException {
        AtomFeedProperties properties = AtomFeedProperties.getInstance();
        String amritServerUrl = properties.getProperty("amrit.patient.uri");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet request = new HttpGet(amritServerUrl);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    AmritPatientSearchResult results = objectMapper.readValue(result, AmritPatientSearchResult.class);
                    System.out.println(results.getData());
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
