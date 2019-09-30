package com.tech.repair.service;

import com.tech.repair.po.*;
import com.tech.repair.repository.RcApplyRepository;
import com.tech.repair.vo.RcVo;
import com.tech.repair.vo.RegisterInfoVo;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RcApplyService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RcApplyRepository rcApplyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RepairCompanyService repairCompanyService;

    @Autowired
    private RcCService rcCService;


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

    public List<RcVo> getRcApplyByRcId(String rcId)
    {
        if (Strings.isNotBlank(rcId)) {
            return doMix(rcApplyRepository.findByRcId(rcId));
        }else
        {
            logger.warn("参数错误-RcApply单位关系");
            return null;
        }
    }

    public RcVo getRcApplyByRcIdAndCompanyId(String rcId,String companyId)
    {
        RcVo rcVo=new RcVo();
        Company c=(Company) companyService.getCompany(companyId);
        RepairCompany rc=(RepairCompany) repairCompanyService.getRepairCompanyByRcId(rcId);
        BeanUtils.copyProperties(c,rcVo);
        BeanUtils.copyProperties(rc,rcVo);
        return rcVo;

    }

    public List<RcVo> getRcApplyByCompanyId(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            return doMix(rcApplyRepository.findByCompanyId(companyId));
        }else {
            logger.warn("参数错误-companyId");
            return null;
        }
    }

    public boolean passedRcApply(String id)
    {
        if (Strings.isNotBlank(id)) {
            RcApply rcApply=  rcApplyRepository.findById(id);
            RcC rcc=new RcC();
            rcc.setRcId(rcApply.getRcId());
            rcc.setCompanyId(rcApply.getCompanyId());
            return
                    rcApplyRepository.passedApply(Integer.valueOf(id)) > 0
                    && rcCService.addRcC(rcc)!=null;

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





    private List<RcVo> doMix(List<RcApply> ri)
    {
        List<RcVo> list=new ArrayList<>();
        for (RcApply i : ri) {
            RcVo rcVo=new RcVo();
            Company c=(Company) companyService.getCompany(i.getCompanyId());
            RepairCompany rc=(RepairCompany) repairCompanyService.getRepairCompanyByRcId(i.getRcId());
            BeanUtils.copyProperties(c,rcVo);
            BeanUtils.copyProperties(rc,rcVo);
            BeanUtils.copyProperties(i,rcVo);
            list.add(rcVo);
        }
        return list;
    }





}
