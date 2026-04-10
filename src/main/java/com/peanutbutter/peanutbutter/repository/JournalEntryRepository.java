package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.JournalEntry;

public interface JournalEntryRepository extends JpaRepository<JournalEntry,Long> {

}
