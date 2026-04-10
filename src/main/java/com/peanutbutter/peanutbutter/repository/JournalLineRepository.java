package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.JournalLine;

public interface JournalLineRepository extends JpaRepository<JournalLine,Long> {

}
