package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.Expence;

public interface ExpenceRepository extends JpaRepository<Expence,Integer> {

}
