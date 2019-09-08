package com.tech.repair.repository;

import com.tech.repair.po.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompany,Integer> {

   List<UserCompany> findByUserOpenId(String userOpenId);

   List<UserCompany> findByCompanyId(String companyId);

   int deleteByCompanyIdAndAndUserOpenId(String companyId,String userOpenId);

   int deleteByCompanyId(String companyId);

   int deleteByUserOpenId(String userOpenId);


}
