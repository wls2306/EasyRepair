package com.tech.repair.repository;

import com.tech.repair.pojo.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Company findByCompanyId(String companyId);

    List<Company> findByCompanyNameLike(String companyName);

    int deleteByCompanyId(String companyId);

}
