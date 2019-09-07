package com.tech.repair.repository;

import com.tech.repair.pojo.CompanyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyQuestionRepository extends JpaRepository<CompanyQuestion,Integer> {

    CompanyQuestion findByCompanyId(String companyId);

    int deleteByCompanyId(String companyId);

}
