package org.bahmni.amrit.integration.repository;

import org.bahmni.amrit.integration.model.QuartzCronScheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronJobRepository extends JpaRepository<QuartzCronScheduler, Integer> {
}
