package com.tech.repair.repository;

import com.tech.repair.pojo.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCompanyRepository extends JpaRepository<UserCompany,Integer> {

   List<UserCompany> findByUserOpenId(String userOpenId);

   List<UserCompany> findByCompanyId(String companyId);

   int deleteByCompanyIdAndAndUserOpenId(String companyId,String userOpenId);

   int deleteByCompanyId(String companyId);

   int deleteByUserOpenId(String userOpenId);


}
