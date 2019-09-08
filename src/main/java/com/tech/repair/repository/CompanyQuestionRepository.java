package com.tech.repair.repository;

import com.tech.repair.po.CompanyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyQuestionRepository extends JpaRepository<CompanyQuestion,Integer> {

    CompanyQuestion findByCompanyId(String companyId);

    int deleteByCompanyId(String companyId);

}
