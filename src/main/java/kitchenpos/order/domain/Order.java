package kitchenpos.order.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kitchenpos.order.exception.CannotChangeOrderStatusException;
import kitchenpos.table.domain.OrderTable;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_table_id")
    private OrderTable orderTable;
    @Column(name = "ordered_time")
    private LocalDateTime orderedTime;
    @Embedded
    private OrderLineItems orderLineItems;

    public Order() {
    }

    public Order(LocalDateTime orderedTime, OrderTable orderTable) {
        this.orderStatus = OrderStatus.COOKING;
        this.orderedTime = orderedTime;
        this.orderTable = orderTable;
        this.orderLineItems = new OrderLineItems();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public OrderLineItems getOrderLineItems() {
        return orderLineItems;
    }

    public void addOrderLineItem(OrderLineItem orderLineItem) {
        this.orderLineItems.add(orderLineItem);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        validateOrderStatusIsCompleted();
        this.orderStatus = orderStatus;
    }

    public OrderTable getOrderTable() {
        return orderTable;
    }

    private void validateOrderStatusIsCompleted() {
        if (orderStatus.equals(OrderStatus.COMPLETION)) {
            throw new CannotChangeOrderStatusException("주문상태가 COMPLETION 인 건은 상태수정이 불가능합니다.");
        }
    }
}
