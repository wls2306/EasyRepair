package com.tech.repair.repository;

import com.tech.repair.po.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress,Integer> {
    List<Progress> findByProgressOrderId(String orderId);


}
