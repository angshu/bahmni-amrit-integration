package org.bahmni.amrit.integration.atomfeed.jobs;

public interface FeedJob {
    void process() throws InterruptedException;
}
