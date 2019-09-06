package com.tech.repair.repository;

import com.tech.repair.pojo.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Company findByCompanyId(String companyId);


}
