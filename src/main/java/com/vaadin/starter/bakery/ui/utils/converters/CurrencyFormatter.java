package com.vaadin.starter.bakery.ui.utils.converters;

import com.vaadin.starter.bakery.ui.dataproviders.DataProviderUtil;
import com.vaadin.starter.bakery.ui.utils.FormattingUtils;

/**
 * Converter for formatting Integer values as currency strings.
 */
public class CurrencyFormatter{
	public String encode(Integer modelValue) {
		return DataProviderUtil.convertIfNotNull(modelValue, FormattingUtils::formatAsCurrency);
	}
}
