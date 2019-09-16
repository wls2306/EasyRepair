package com.tech.repair.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.repair.po.Progress;
import com.tech.repair.repository.ProgressRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProgressService {
    Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private ProgressRepository progressRepository;
    /**
    * @Author: Wls
    * @Date: 13:41 2019/9/14
    * @Description: 新增 progress
    */
    public Progress addProgress(Progress p)throws Exception
    {
        if (Strings.isNotEmpty(p.getProgressOrderId())) {
            ObjectMapper mapper=new ObjectMapper();
            logger.info("progress saved :{{}}",mapper.writeValueAsString(p));
            return progressRepository.save(p);
        }else
        {
            logger.warn("新增 progress -参数错误");
            return null;
        }
    }

    public List<Progress> getProgressById(String orderId)
    {
        if (Strings.isNotEmpty(orderId)) {
            return progressRepository.findByProgressOrderId(orderId);
        }else
        {
            logger.warn("查询progress失败，参数错误");
            return null;
        }

    }
}
