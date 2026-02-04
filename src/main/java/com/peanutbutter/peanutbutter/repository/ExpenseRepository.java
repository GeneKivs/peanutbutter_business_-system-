package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {

}
