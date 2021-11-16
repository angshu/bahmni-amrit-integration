package org.bahmni.amrit.integration.atomfeed.contract.encounter;

import org.bahmni.amrit.integration.atomfeed.builders.OpenMRSEncounterBuilder;
import org.bahmni.amrit.integration.atomfeed.builders.OpenMRSOrderBuilder;
import org.bahmni.amrit.integration.atomfeed.builders.OrderTypeBuilder;
import org.bahmni.amrit.integration.model.OrderType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OpenMRSEncounterTest {

    @Test
    public void testGetAcceptableTestOrders() throws Exception {

        OpenMRSOrder acceptableOrder = new OpenMRSOrderBuilder().withOrderType("radiology").withOrderUuid("order-1").build();
        OpenMRSOrder unknownOrder = new OpenMRSOrderBuilder().withOrderType("lab").withOrderUuid("order-2").build();
        OpenMRSEncounter openMRSEncounter = new OpenMRSEncounterBuilder().withTestOrder(acceptableOrder).withTestOrder(unknownOrder).build();
        OrderType orderType = new OrderTypeBuilder().withName("radiology").build();

        List<OpenMRSOrder> acceptableTestOrders = openMRSEncounter.getAcceptableTestOrders(Arrays.asList(orderType));

        assertEquals(1, acceptableTestOrders.size());
        assertEquals(acceptableOrder, acceptableTestOrders.get(0));
    }
}