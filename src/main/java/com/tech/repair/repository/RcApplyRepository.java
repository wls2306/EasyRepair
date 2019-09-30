package com.tech.repair.repository;

import com.tech.repair.po.RcApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RcApplyRepository  extends JpaRepository<RcApply,Integer> {

    @Query("update RcApply set status=1 where id=?1")
    int passedApply(Integer id);

    @Query("update RcApply set status=-1 where id=?1")
    int rejectApply(Integer id);

    RcApply findById(String id);

    RcApply findByRcIdAndCompanyId(String rcId,String companyId);

    List<RcApply> findByRcId(String rcId);

    List<RcApply> findByCompanyId(String companyId);

    int deleteById(String id);



}
