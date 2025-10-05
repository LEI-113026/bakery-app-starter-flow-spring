package com.vaadin.starter.bakery.backend.data.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Customer entity for storing customer information.
 */

@Entity
public class Customer extends AbstractEntity {

	@NotBlank
	@Size(max = 255)
	private String fullName;

	@NotBlank
	@Size(max = 20, message = "{bakery.phone.number.invalid}")
	// A simple phone number checker, allowing an optional international prefix
	// plus a variable number of digits that could be separated by dashes or
	// spaces
	@Pattern(regexp = "^(\\+\\d+)?([ -]?\\d+){4,14}$", message = "{bakery.phone.number.invalid}")
	private String phoneNumber;
	
	@Size(max = 255)
	private String details;
	/**
	 * Gets the full name of the customer.
	 *
	 * @return the full name
	 */

	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name of the customer.
	 *
	 * @param fullName the full name to set
	 */

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the phone number of the customer.
	 *
	 * @return the phone number
	 */

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number of the customer.
	 *
	 * @param phoneNumber the phone number to set
	 */

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets additional details about the customer.
	 *
	 * @return the details
	 */

	public String getDetails() {
		return details;
	}

	/**
	 * Sets additional details about the customer.
	 *
	 * @param details the details to set
	 */

	public void setDetails(String details) {
		this.details = details;
	}

}
