package com.peanutbutter.peanutbutter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peanutbutter.peanutbutter.model.Location;

public interface LocationRepository extends JpaRepository<Location,Integer> {

}
