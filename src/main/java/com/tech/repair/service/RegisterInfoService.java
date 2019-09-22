package com.tech.repair.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.repair.po.Company;
import com.tech.repair.po.RegisterInfo;
import com.tech.repair.po.User;
import com.tech.repair.po.UserCompany;
import com.tech.repair.repository.RegisterInfoRepository;
import com.tech.repair.vo.RegisterInfoVo;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RegisterInfoService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RegisterInfoRepository registerInfoRepository;
    @Autowired
    private UserCompanyService userCompanyService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;


    /**
    * @Author: Wls
    * @Date: 10:08 2019/9/8
    * @Description Add Register Info To Database
    */
    public RegisterInfo addRegisterInfo(RegisterInfo ri)throws Exception
    {
        ri.setRegisterStatus("0");
        if (Strings.isNotBlank(ri.getCompanyId())&&Strings.isNotBlank(ri.getOpenId())) {
            ObjectMapper mapper=new ObjectMapper();
            logger.info("add RI --{{}}",mapper.writeValueAsString(ri));
            return (RegisterInfo) registerInfoRepository.save(ri);
        }
        else
        {
            logger.warn(" add RI -- parameter null");
            return null;
        }
    }


    public boolean AcceptApplyByOpenIdAndCompanyId(String openId,String companyId)
    {
        if (Strings.isNotBlank(openId)&&Strings.isNotBlank(companyId)) {
            UserCompany uc=new UserCompany();
            uc.setUserOpenId(openId);
            uc.setCompanyId(companyId);
            registerInfoRepository.acceptApplyByOpenId(openId, companyId);
            return Strings.isNotBlank(userCompanyService.addUserCompany(uc).getUserOpenId()) ;
        }else
        {
            logger.warn("Accept Apply - parameter null");
            return false;
        }
    }

    public boolean RefuseApplyByOpenIdAndCompanyId(String openId,String companyId)
    {
        if (Strings.isNotBlank(openId)&&Strings.isNotBlank(companyId)) {
            return registerInfoRepository.refuseApplyByOpenId (openId, companyId) > 0 ;
        }else
        {
            logger.warn("Refuse Apply - parameter null");
            return false;
        }
    }

    public boolean DeleteApplyByOpenIdAndCompanyId(String openId,String companyId)
    {
        if (Strings.isNotBlank(openId)&&Strings.isNotBlank(companyId)) {
            return registerInfoRepository.deleteByOpenIdAndCompanyId(openId, companyId)>0;
        }else
        {
            logger.warn("Delete - parameter null");
            return false;
        }
    }

    public List<RegisterInfoVo> findRegisterByCompanyId(String companyId)
    {
        if (Strings.isNotEmpty(companyId)) {
            ArrayList<RegisterInfoVo> list=new ArrayList<>();
            List<RegisterInfoVo> rs= doMix(registerInfoRepository.findByCompanyId(companyId));
            return rs;
        }else
        {
            logger.warn("Find By CompanyId Err-- parameter null");
            return null;
        }
    }

    public List<RegisterInfoVo> findByUserOpenId(String userOpenId)
    {

        if (Strings.isNotEmpty(userOpenId)) {
            ArrayList<RegisterInfoVo> list=new ArrayList<>();
            List<RegisterInfoVo> rs= doMix(registerInfoRepository.findByOpenId(userOpenId));
            return rs;
        }else
        {
            logger.warn("Find By Open Id Err- parameter is null");
            return null;
        }
    }


    /**
    * @Author: Wls
    * @Date: 12:41 2019/9/8
    * @Description: 工具类：分别查询User的详细信息和Company的详细信息，混合在一起交给前端展示处理
    */
    public List<RegisterInfoVo> doMix(List<RegisterInfo> ri)
    {
        List<RegisterInfoVo> list=new ArrayList<>();
        for (RegisterInfo registerInfo : ri) {
            RegisterInfoVo riv=new RegisterInfoVo();
            User u=(User)userService.getUser(registerInfo.getOpenId());
            Company c=(Company) companyService.getCompany(registerInfo.getCompanyId());
            BeanUtils.copyProperties(u,riv);
            BeanUtils.copyProperties(c,riv);
            BeanUtils.copyProperties(registerInfo,riv);
            list.add(riv);
        }
        return list;
    }

}
