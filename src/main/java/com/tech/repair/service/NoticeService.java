package com.tech.repair.service;

import com.tech.repair.po.Notice;
import com.tech.repair.repository.NoticeRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    private Logger logger= LoggerFactory.getLogger(getClass());


    public Notice saveNotice(Notice n)
    {
        if (Strings.isNotBlank(n.getNoticeTitle())) {
            return noticeRepository.save(n);
        }else
        {
            logger.warn("新建通知-参数错误");
            return null;
        }

    }


   public List<Notice> findNoticeByCompanyId(String companyId)
   {
       if (Strings.isBlank(companyId)) {
           return noticeRepository.findByNoticeCompanyIdAndNoticeStatus(companyId,"1");
       }else
       {
           logger.warn("根据单位Id查通知 错误-参数为空");
           return null;
       }
   }

   public List<Notice> findPublicNotice()
   {
       return noticeRepository.findByNoticeTypeAndNoticeStatus("2","1");
   }

   public boolean deleteNoticeById(String id)
   {
       if (Strings.isNotBlank(id)) {
           return noticeRepository.deleteNoticeById(Integer.valueOf(id)) > 0;
       }
       else
       {
           logger.warn("删除通知-参数错误");
           return false;
       }
   }






}
