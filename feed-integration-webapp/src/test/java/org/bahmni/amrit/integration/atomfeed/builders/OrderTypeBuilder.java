package org.bahmni.amrit.integration.atomfeed.builders;

import org.bahmni.amrit.integration.model.OrderType;

public class OrderTypeBuilder {
    private OrderType orderType;

    public OrderTypeBuilder() {
        orderType = new OrderType();
    }

    public OrderTypeBuilder withName(String orderTypeName) {
        orderType.setName(orderTypeName);
        return this;
    }

    public OrderType build() {
        return orderType;
    }
}
