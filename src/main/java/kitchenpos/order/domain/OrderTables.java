package kitchenpos.order.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import org.springframework.util.CollectionUtils;

@Embeddable
public class OrderTables {
    @OneToMany(mappedBy = "tableGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderTable> orderTables;

    protected OrderTables() {
    }

    public OrderTables(List<OrderTable> orderTables) {
        validate(orderTables);
        this.orderTables = orderTables;
    }

    public List<OrderTable> list() {
        return this.orderTables;
    }

    public boolean sameSizeWith(int size) {
        return this.orderTables.size() == size;
    }

    public void updateTableGroup(TableGroup tableGroup) {
        orderTables.forEach(table -> table.updateTableGroup(tableGroup));
    }

    public void clear() {
        this.orderTables.clear();
    }

    private void validate(List<OrderTable> orderTables) {
        checkTableSize(orderTables);
        for(OrderTable orderTable : orderTables) {
            checkEmptyOrAlreadyGroup(orderTable);
        }
    }

    private void checkTableSize(List<OrderTable> orderTables) {
        if (CollectionUtils.isEmpty(orderTables) || orderTables.size() < 2) {
            throw new IllegalArgumentException("2개 미만의 테이블은 단체 지정이 불가합니다.");
        }
    }

    private void checkEmptyOrAlreadyGroup(OrderTable orderTable) {
        if (!orderTable.isEmpty() || Objects.nonNull(orderTable.getTableGroupId())) {
            throw new IllegalArgumentException("비어있지 않거나 이미 단체 지정된 테이블은 단체 지정이 불가합니다.");
        }
    }
}
