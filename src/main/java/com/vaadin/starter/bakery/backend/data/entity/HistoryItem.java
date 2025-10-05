package com.vaadin.starter.bakery.backend.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.vaadin.starter.bakery.backend.data.OrderState;

/**
 * HistoryItem entity for storing history of changes made to orders.
 */

@Entity
public class HistoryItem extends AbstractEntity {

	private OrderState newState;

	@NotBlank
	@Size(max = 255)
	private String message;

	@NotNull
	private LocalDateTime timestamp;
	@ManyToOne
	@NotNull
	private User createdBy;

	HistoryItem() {
		// Empty constructor is needed by Spring Data / JPA
	}

	/**
	 * Constructs a HistoryItem with the specified creator and message.
	 * The timestamp is set to the current time.
	 *
	 * @param createdBy the user who created the history item
	 * @param message   the message describing the history item
	 */

	public HistoryItem(User createdBy, String message) {
		this.createdBy = createdBy;
		this.message = message;
		timestamp = LocalDateTime.now();
	}

	/**
	 * Constructs a HistoryItem with the specified creator, message, and new state.
	 * The timestamp is set to the current time.
	 *
	 * @param createdBy the user who created the history item
	 * @param message   the message describing the history item
	 * @param newState  the new state associated with the history item
	 */

	public OrderState getNewState() {
		return newState;
	}

	/**
	 * Sets the new state associated with the history item.
	 *
	 * @param newState the new state to set
	 */

	public void setNewState(OrderState newState) {
		this.newState = newState;
	}

	/**
	 * Gets the message describing the history item.
	 *
	 * @return the message
	 */

	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message describing the history item.
	 *
	 * @param message the message to set
	 */

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the timestamp of when the history item was created.
	 *
	 * @return the timestamp
	 */

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp of when the history item was created.
	 *
	 * @param timestamp the timestamp to set
	 */

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the user who created the history item.
	 *
	 * @return the user who created the history item
	 */

	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the user who created the history item.
	 *
	 * @param createdBy the user to set
	 */

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
