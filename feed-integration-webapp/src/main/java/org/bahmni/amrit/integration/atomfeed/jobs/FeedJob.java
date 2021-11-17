package org.bahmni.amrit.integration.atomfeed.jobs;

import java.io.IOException;
import java.text.ParseException;

public interface FeedJob {
    void process() throws InterruptedException, IOException, ParseException;
}
