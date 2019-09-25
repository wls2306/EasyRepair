package com.tech.repair.repository;

import com.tech.repair.po.RcC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RcCRepository extends JpaRepository<RcC,Integer> {
    List<RcC> findByCompanyId(String companyId);

    List<RcC> findByRcId(String rcId);

    int deleteByRcIdAndCompanyId(String rcId,String companyId);

}
