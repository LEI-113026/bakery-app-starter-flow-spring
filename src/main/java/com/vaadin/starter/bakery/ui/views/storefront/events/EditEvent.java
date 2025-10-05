package com.vaadin.starter.bakery.ui.views.storefront.events;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

/**
 * Event representing an edit action on a component.
 */
public class EditEvent extends ComponentEvent<Component> {

	public EditEvent(Component source) {
		super(source, false);
	}
}
