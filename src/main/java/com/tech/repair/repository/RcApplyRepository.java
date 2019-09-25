package com.tech.repair.repository;

import com.tech.repair.po.RcApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RcApplyRepository  extends JpaRepository<RcApply,Integer> {

    @Query("update RcApply set status=1 where rcId=?1")
    int passedApply(String id);

    @Query("update RcApply set status=-1 where rcId=?1")
    int rejectApply(String id);

    List<RcApply> findByRcId(String rcId);

    List<RcApply> findByCompanyId(String companyId);

    int deleteByRcId(String id);

}
