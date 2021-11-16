package org.bahmni.amrit.integration.repository;

import org.bahmni.amrit.integration.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderUuid(String orderUuid);
}
