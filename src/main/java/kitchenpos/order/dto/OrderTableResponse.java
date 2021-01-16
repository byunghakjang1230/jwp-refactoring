package kitchenpos.order.dto;

import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.order.domain.OrderTable;

public class OrderTableResponse {
	private Long id;
	private Long tableGroupId;
	private int numberOfGuests;
	private boolean empty;

	protected OrderTableResponse() {
	}

	public OrderTableResponse(Long id, Long tableGroupId, int numberOfGuests, boolean empty) {
		this.id = id;
		this.tableGroupId = tableGroupId;
		this.numberOfGuests = numberOfGuests;
		this.empty = empty;
	}

	public Long getId() {
		return id;
	}

	public Long getTableGroupId() {
		return tableGroupId;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public boolean isEmpty() {
		return empty;
	}

	public static OrderTableResponse of(OrderTable orderTable) {
		return new OrderTableResponse(orderTable.getId()
			, orderTable.getTableGroupId()
			, orderTable.getNumberOfGuests()
			, orderTable.isEmpty());
	}

	public static List<OrderTableResponse> ofList(List<OrderTable> orderTables) {
		return orderTables.stream()
			.map(OrderTableResponse::of)
			.collect(Collectors.toList());
	}
}