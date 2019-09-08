package com.tech.repair.repository;

import com.tech.repair.po.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<System,Integer> {
    System findAllById(Integer id);
}
