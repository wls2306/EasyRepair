package com.tech.repair.repository;
import com.tech.repair.po.RegisterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterInfoRepository extends JpaRepository<RegisterInfo,Integer> {
    @Modifying
    @Query("update RegisterInfo set registerStatus=1 where openId=?1 and companyId=?2")
    int acceptApplyByOpenId(String openId,String companyId);

    @Query("update RegisterInfo set registerStatus=-1 where openId=?1 and companyId=?2")
    int refuseApplyByOpenId(String openId,String companyId);

    int deleteByOpenIdAndCompanyId(String openId, String companyId);

    List<RegisterInfo> findByCompanyId(String companyId);

    List<RegisterInfo> findByOpenId(String userOpenId);

    RegisterInfo findByCompanyIdAndOpenId(String companyId,String openId);
}
