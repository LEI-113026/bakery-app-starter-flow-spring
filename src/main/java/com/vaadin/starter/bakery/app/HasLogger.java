package com.vaadin.starter.bakery.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HasLogger is a feature interface that provides Logging capability for anyone
 * implementing it where logger needs to operate in serializable environment
 * without being static.
 */
public interface HasLogger {

	/**
	 * Provides a logger for the implementing class.
	 *
	 * @return a logger instance
	 */

	default Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
}
