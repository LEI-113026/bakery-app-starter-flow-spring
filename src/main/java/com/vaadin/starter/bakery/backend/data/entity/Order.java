package com.vaadin.starter.bakery.backend.data.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import com.vaadin.starter.bakery.backend.data.OrderState;

/**
 * Order entity representing a customer's order in the bakery system.
 */

@Entity(name = "OrderInfo") // "Order" is a reserved word
@NamedEntityGraphs({@NamedEntityGraph(name = Order.ENTITY_GRAPTH_BRIEF, attributeNodes = {
		@NamedAttributeNode("customer"),
		@NamedAttributeNode("pickupLocation")
}),@NamedEntityGraph(name = Order.ENTITY_GRAPTH_FULL, attributeNodes = {
		@NamedAttributeNode("customer"),
		@NamedAttributeNode("pickupLocation"),
		@NamedAttributeNode("items"),
		@NamedAttributeNode("history")
})})
@Table(indexes = @Index(columnList = "dueDate"))
public class Order extends AbstractEntity implements OrderSummary {

	public static final String ENTITY_GRAPTH_BRIEF = "Order.brief";
	public static final String ENTITY_GRAPTH_FULL = "Order.full";

	@NotNull(message = "{bakery.due.date.required}")
	private LocalDate dueDate;

	@NotNull(message = "{bakery.due.time.required}")
	private LocalTime dueTime;

	@NotNull(message = "{bakery.pickup.location.required}")
	@ManyToOne
	private PickupLocation pickupLocation;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@OrderColumn
	@JoinColumn
	@BatchSize(size = 1000)
	@NotEmpty
	@Valid
	private List<OrderItem> items;
	@NotNull(message = "{bakery.status.required}")
	private OrderState state;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderColumn
	@JoinColumn
	private List<HistoryItem> history;

	/**
	 * Constructs a new Order with the specified creator.
	 * The order is initialized with a NEW state, an empty customer, and an initial
	 * history item indicating that the order was placed.
	 *
	 * @param createdBy the user who created the order
	 */

	public Order(User createdBy) {
		this.state = OrderState.NEW;
		setCustomer(new Customer());
		addHistoryItem(createdBy, "Order placed");
		this.items = new ArrayList<>();
	}

	Order() {
		// Empty constructor is needed by Spring Data / JPA
	}

	/**
	 * Adds a history item to the order's history.
	 * The history item records the user who created it, a comment, and the current
	 * state of the order.
	 *
	 * @param createdBy the user who created the history item
	 * @param comment   the comment describing the history item
	 */

	public void addHistoryItem(User createdBy, String comment) {
		HistoryItem item = new HistoryItem(createdBy, comment);
		item.setNewState(state);
		if (history == null) {
			history = new LinkedList<>();
		}
		history.add(item);
	}

	/**
	 * Adds a history item to the order's history with a specified new state.
	 * The history item records the user who created it, a comment, and the new
	 * state of the order.
	 *
	 * @param createdBy the user who created the history item
	 * @param comment   the comment describing the history item
	 * @param newState  the new state associated with the history item
	 */

	@Override
	public LocalDate getDueDate() {
		return dueDate;
	}

	/** Sets the due date of the order.
	 *
	 * @param dueDate the due date to set
	 */

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public LocalTime getDueTime() {
		return dueTime;
	}

	/** Sets the dueTime of the order
	 *
	 * @param dueTime
	 */
	public void setDueTime(LocalTime dueTime) {
		this.dueTime = dueTime;
	}

	/** Gets the Pickup Location of an Order
	 *
	 * @return
	 */
	@Override
	public PickupLocation getPickupLocation() {
		return pickupLocation;
	}

	/** Sets the Pickup Location of the Order
	 *
	 * @param pickupLocation
	 */
	public void setPickupLocation(PickupLocation pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	
	@Override
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public List<HistoryItem> getHistory() {
		return history;
	}

	public void setHistory(List<HistoryItem> history) {
		this.history = history;
	}

	@Override
	public OrderState getState() {
		return state;
	}

	public void changeState(User user, OrderState state) {
		boolean createHistory = this.state != state && this.state != null && state != null;
		this.state = state;
		if (createHistory) {
			addHistoryItem(user, "Order " + state);
		}
	}

	@Override
	public String toString() {
		return "Order{" + "dueDate=" + dueDate + ", dueTime=" + dueTime + ", pickupLocation=" + pickupLocation
				+ ", customer=" + customer + ", items=" + items + ", state=" + state + '}';
	}

	@Override
	public Integer getTotalPrice() {
		return items.stream().map(i -> i.getTotalPrice()).reduce(0, Integer::sum);
	}
}
