package org.bahmni.amrit.integration.atomfeed.jobs;

import java.io.IOException;

public interface FeedJob {
    void process() throws InterruptedException, IOException;
}
