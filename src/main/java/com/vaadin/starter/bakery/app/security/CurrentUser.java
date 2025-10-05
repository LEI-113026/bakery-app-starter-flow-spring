package com.vaadin.starter.bakery.app.security;

import com.vaadin.starter.bakery.backend.data.entity.User;

/**
 * Functional interface to get the current authenticated user.
 */

@FunctionalInterface
public interface CurrentUser {

	User getUser();
}
