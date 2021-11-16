package org.bahmni.amrit.integration.repository;


import org.bahmni.amrit.integration.model.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {

    OrderType getByName(String name);

}
