package com.tech.repair.service;

import com.tech.repair.po.RcApply;
import com.tech.repair.repository.RcApplyRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RcApplyService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RcApplyRepository rcApplyRepository;

    public RcApply addRcApply(RcApply rcApply)
    {
        if (Strings.isNotBlank(rcApply.getCompanyId())&&Strings.isNotBlank(rcApply.getRcId())) {
            return rcApplyRepository.save(rcApply);
        }else
        {
            logger.warn("参数错误-新增 维修单位与 单位关系");
            return null;
        }
    }

    public List<RcApply> getRcApplyByRcId(String rcId)
    {
        if (Strings.isNotBlank(rcId)) {
            return rcApplyRepository.findByRcId(rcId);
        }else
        {
            logger.warn("参数错误-RcApply单位关系");
            return null;
        }
    }

    public List<RcApply> getRcApplyByCompanyId(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            return rcApplyRepository.findByCompanyId(companyId);
        }else {
            logger.warn("参数错误-companyId");
            return null;
        }
    }

    public boolean passedRcApply(String id)
    {
        if (Strings.isNotBlank(id)) {
            return rcApplyRepository.passedApply(Integer.valueOf(id)) > 0;
        }else{
            logger.warn("参数错误-passedRcApply");
            return false;
        }

    }

    public boolean rejectRcAplly(String id)
    {
        if (Strings.isNotBlank(id)) {
            return rcApplyRepository.rejectApply(Integer.valueOf(id)) > 0;
        }else{
            logger.warn("参数错误-passedRcApply");
            return false;
        }
    }

    public boolean deleteRcApply(String id)
    {
        if (Strings.isNotBlank(id)) {
            return rcApplyRepository.deleteById(id)>0;
        }else
        {
            logger.warn("参数错误-deleteRcApply");
            return false;
        }
    }







}
