package kitchenpos.utils.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;

public class OrderTableObjects {
    private final OrderTable orderTable1;
    private final OrderTable orderTable2;
    private final OrderTable orderTable3;
    private final OrderTable orderTable4;
    private final OrderTable orderTable5;
    private final OrderTable orderTable6;
    private final OrderTable orderTable7;
    private final OrderTable orderTable8;

    public OrderTableObjects() {
        orderTable1 = new OrderTable(0, true);
        orderTable1.setId(1L);
        orderTable2 = new OrderTable(0, true);
        orderTable2.setId(2L);
        orderTable3 = new OrderTable(0, true);
        orderTable3.setId(3L);
        orderTable4 = new OrderTable(0, true);
        orderTable4.setId(4L);
        orderTable5 = new OrderTable(0, true);
        orderTable5.setId(5L);
        orderTable6 = new OrderTable(0, true);
        orderTable6.setId(6L);
        orderTable7 = new OrderTable(0, true);
        orderTable7.setId(7L);
        orderTable8 = new OrderTable(0, true);
        orderTable8.setId(8L);
    }

    public OrderTable getOrderTable1() {
        return orderTable1;
    }

    public OrderTable getOrderTable2() {
        return orderTable2;
    }

    public OrderTable getOrderTable3() {
        return orderTable3;
    }

    public OrderTable getOrderTable4() {
        return orderTable4;
    }

    public OrderTable getOrderTable5() {
        return orderTable5;
    }

    public OrderTable getOrderTable6() {
        return orderTable6;
    }

    public OrderTable getOrderTable7() {
        return orderTable7;
    }

    public OrderTable getOrderTable8() {
        return orderTable8;
    }

    public OrderTableRequest getOrderTableRequest1() {
        return new OrderTableRequest(orderTable1.getNumberOfGuests(), orderTable1.isEmpty());
    }

    public OrderTableRequest getOrderTableRequest2() {
        return new OrderTableRequest(orderTable2.getNumberOfGuests(), orderTable2.isEmpty());
    }

    public OrderTableRequest getOrderTableRequest3() {
        return new OrderTableRequest(orderTable3.getNumberOfGuests(), orderTable3.isEmpty());
    }

    public OrderTableRequest getOrderTableRequest4() {
        return new OrderTableRequest(orderTable4.getNumberOfGuests(), orderTable4.isEmpty());
    }

    public OrderTableRequest getOrderTableRequest5() {
        return new OrderTableRequest(orderTable5.getNumberOfGuests(), orderTable5.isEmpty());
    }

    public OrderTableRequest getOrderTableRequest6() {
        return new OrderTableRequest(orderTable6.getNumberOfGuests(), orderTable6.isEmpty());
    }

    public OrderTableRequest getOrderTableRequest7() {
        return new OrderTableRequest(orderTable7.getNumberOfGuests(), orderTable7.isEmpty());
    }

    public OrderTableRequest getOrderTableRequest8() {
        return new OrderTableRequest(orderTable8.getNumberOfGuests(), orderTable8.isEmpty());
    }

    public List<OrderTable> getOrderTables() {
        return new ArrayList<>(Arrays.asList(orderTable1, orderTable2, orderTable3, orderTable4, orderTable5, orderTable6, orderTable7, orderTable8));
    }

    public List<OrderTableResponse> getOrderTableResponses() {
        return getOrderTables().stream()
                .map(OrderTableResponse::of)
                .collect(Collectors.toList());
    }
}
