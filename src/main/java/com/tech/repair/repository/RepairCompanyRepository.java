package com.tech.repair.repository;

import com.tech.repair.po.RepairCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairCompanyRepository extends JpaRepository<RepairCompany,Integer> {

    RepairCompany findByRcHost(String rcHost);

    RepairCompany findByRcId(String rcId);

    int deleteByRcId(String rcId);



}
