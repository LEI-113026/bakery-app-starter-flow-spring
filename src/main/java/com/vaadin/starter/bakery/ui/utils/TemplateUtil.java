package com.vaadin.starter.bakery.ui.utils;

/**
 * Utility class for generating template locations.
 */

public class TemplateUtil {

	public static String generateLocation(String basePage, String entityId) {
		return basePage + (entityId == null || entityId.isEmpty() ? "" : "/" + entityId);
	}
}
