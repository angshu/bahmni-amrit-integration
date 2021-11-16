package org.bahmni.amrit.integration.repository;

import org.bahmni.amrit.integration.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
