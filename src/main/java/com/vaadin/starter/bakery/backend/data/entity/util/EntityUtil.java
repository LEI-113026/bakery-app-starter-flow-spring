package com.vaadin.starter.bakery.backend.data.entity.util;

import com.vaadin.starter.bakery.backend.data.entity.AbstractEntity;
/**
 * Utility class for entity-related operations.
 */

public final class EntityUtil {
	/**
	 *
	 * @param type
	 * @return
	 */

	public static final String getName(Class<? extends AbstractEntity> type) {
		// All main entities have simple one word names, so this is sufficient. Metadata
		// could be added to the class if necessary.
		return type.getSimpleName();
	}
}
