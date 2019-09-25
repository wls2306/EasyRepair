package com.tech.repair.controller;

import com.tech.repair.po.RegisterInfo;
import com.tech.repair.service.RegisterInfoService;
import com.tech.repair.vo.RegisterInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户注册信息模块")
@RequestMapping("/api/ri")
public class RegisterInfoController {

    @Autowired
    private RegisterInfoService registerInfoService;


    /**
    * @Author: Wls
    * @Date: 10:25 2019/9/8
    * @Description: 添加注册信息
    */
    @PostMapping("/")
    @ApiOperation(value = "增添注册申请信息",notes = "注意：companyId 与 openId 要与User表中的一致！")
    public RegisterInfo addRegisterInfo(RegisterInfo registerInfo)throws Exception
    {
        return registerInfoService.addRegisterInfo(registerInfo);
    }

    /**
    * @Author: Wls
    * @Date: 10:27 2019/9/8
    * @Description:通过注册信息
    */
    @PutMapping("/accept")
    @ApiOperation(value = "通过注册申请",notes = "在此处通过申请回自动向 用户-单位 表中建立用户-单位联系。")
    public boolean acceptApplyByOpenIdAndCompanyId(String userOpenId,String companyId)
    {
        return registerInfoService.AcceptApplyByOpenIdAndCompanyId(userOpenId, companyId);
    }

    /** 
    * @Author: Wls 
    * @Date: 10:36 2019/9/8 
    * @Description: 
    */
    @PutMapping("/refuse")
    @ApiOperation(value = "驳回注册申请")
    public boolean refuseApplyByOpenIdAndCompanyId(String userOpenId,String companyId)
    {
        return registerInfoService.RefuseApplyByOpenIdAndCompanyId(userOpenId, companyId);
    }

    /** 
    * @Author: Wls 
    * @Date: 10:38 2019/9/8 
    * @Description: 
    */
    @GetMapping("/company")
    @ApiOperation(value = "根据CompanyId查询注册申请",notes = "查询结果包含 User、Company、RegisterInfo 的所有信息")
    public List<RegisterInfoVo> findByCompanyId(String companyId)
    {
        return registerInfoService.findRegisterByCompanyId(companyId);
    }
    
    /** 
    * @Author: Wls 
    * @Date: 10:46 2019/9/8 
    * @Description: 
    */
    @GetMapping("/user")
    @ApiOperation(value = "根据userOpenId查询申请",notes = "查询结果包含 User、Company、RegisterInfo 的所有信息")
    public List<RegisterInfoVo> findByUserOpenId(String userOpenId)
    {
        return registerInfoService.findByUserOpenId(userOpenId);
    }
    
    /**
     * @Author:Wls
     * @Date:21:28 2019/9/16
     * @Description: 
     */
    @GetMapping("/uc")
    @ApiOperation(value = "根据单位编号和个人OpenId查找唯一申请")
    public RegisterInfo findByOpenIdAndCompanyId(String companyId,String userOpenId)
    {
        return registerInfoService.findByCompanyIdAndUserOpenId(companyId, userOpenId);
    }
    

}
