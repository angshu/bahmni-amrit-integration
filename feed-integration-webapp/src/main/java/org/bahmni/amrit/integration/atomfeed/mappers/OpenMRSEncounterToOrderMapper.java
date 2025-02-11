package org.bahmni.amrit.integration.atomfeed.mappers;

import org.bahmni.amrit.integration.atomfeed.contract.encounter.OpenMRSEncounter;
import org.bahmni.amrit.integration.atomfeed.contract.encounter.OpenMRSOrder;
import org.bahmni.amrit.integration.model.Order;
import org.bahmni.amrit.integration.model.OrderType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OpenMRSEncounterToOrderMapper {
    public Order map(OpenMRSOrder openMRSOrder, OpenMRSEncounter openMRSEncounter, List<OrderType> orderTypes) {
        String providerName = getProviderName(openMRSEncounter);
        Order order = new Order();
        order.setOrderNumber(openMRSOrder.getOrderNumber());
        order.setOrderUuid(openMRSOrder.getUuid());
        order.setTestName(openMRSOrder.getConcept().getName().getName());
        order.setTestUuid(openMRSOrder.getConcept().getUuid());
        order.setOrderType(findOrderType(orderTypes, openMRSOrder.getOrderType()));
        order.setDateCreated(new Date());
        order.setCreator(providerName);
        order.setComment(openMRSOrder.getCommentToFulfiller());
        return order;
    }

    private String getProviderName(OpenMRSEncounter openMRSEncounter) {
        return openMRSEncounter.getProviders().size() > 0 ? openMRSEncounter.getProviders().get(0).getName() != null ? openMRSEncounter.getProviders().get(0).getName() : openMRSEncounter.getProviders().get(0).getUuid() : null;
    }

    private OrderType findOrderType(List<OrderType> acceptableOrderTypes, String orderType) {
        for (OrderType acceptableOrderType : acceptableOrderTypes) {
            if (acceptableOrderType.getName().equals(orderType)) {
                return acceptableOrderType;
            }
        }
        return null;
    }

}
