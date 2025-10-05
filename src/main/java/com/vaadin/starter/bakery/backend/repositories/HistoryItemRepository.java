package com.vaadin.starter.bakery.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.starter.bakery.backend.data.entity.HistoryItem;

/**
 * Repository interface for managing HistoryItem entities.
 */
public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
