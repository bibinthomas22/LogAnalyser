package com.example.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LogAlert;


@Repository
public interface AlertRepository extends CrudRepository<LogAlert, String> {
}

