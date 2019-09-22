package com.tech.repair.repository;

import com.tech.repair.po.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer> {
    List<Notice> findByNoticeCompanyIdAndNoticeStatus(String companyId,String status);

    List<Notice> findByNoticeTypeAndNoticeStatus(String type,String status);

    int deleteNoticeById(Integer id);


}
