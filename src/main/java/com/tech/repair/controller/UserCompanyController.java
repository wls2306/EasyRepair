package com.tech.repair.controller;

import com.tech.repair.po.Company;
import com.tech.repair.po.User;
import com.tech.repair.po.UserCompany;
import com.tech.repair.service.UserCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uc/")
@Api(tags = "用户单位关系模块",value = "132456")
public class UserCompanyController {

    private final Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private UserCompanyService userCompanyService;
    /**
     * @Author: Wls
     * @Date: 17:53 2019/9/7
     * @Description: 新增用户-单位关系
     */
    @ApiOperation(value = "新增关系",notes = "★★ 注意！添加时，请确认User表中的userOpenId，以及Company表中的companyId 与添加数据一致！不然会报错！★★")
    @PostMapping("/")
    public UserCompany addUserCompany(UserCompany uc)
    {
        return userCompanyService.addUserCompany(uc);
    }
    /**
     * @Author: Wls
     * @Date: 17:57 2019/9/7
     * @Description: 查询所有与该单位建立联系的用户信息
     */
    @ApiOperation(value = "根据单位Id查询下属用户信息User")
    @GetMapping("/company")
    public List<User> getUserByCompanyId(String companyId)
    {
        logger.info(companyId);
        return userCompanyService.getUserByCompanyId(companyId);
    }

    /**
     * @Author: Wls
     * @Date: 18:01 2019/9/7
     * @Description: 查询用户所建立联系的单位信息
     */
    @ApiOperation(value = "查询用户所绑定的单位信息Company")
    @GetMapping("/user")
    public List<Company> getCompanyByUserOpenId(String userOpenId)
    {
        return userCompanyService.getCompanyByUserOpenId(userOpenId);
    }

    /**
    * @Author: Wls
    * @Date: 18:45 2019/9/7
    * @Description: 解约 用户-单位
    */
    @ApiOperation(value = "解约 用户-单位")
    @DeleteMapping("/release")
    public boolean release(String userOpenId, String companyId)
    {
        return userCompanyService.deleteByUserOpenIdAndCompanyId(userOpenId,companyId);
    }

    /**
    * @Author: Wls
    * @Date: 18:52 2019/9/7
    * @Description: 删除单位下属的所有用户绑定记录
    */
    @ApiOperation(value = "删除单位下属的所有绑定记录")
    @DeleteMapping("/company")
    public boolean deleteByCompanyId(String companyId)
    {
        return userCompanyService.deleteByCompanyId(companyId);
    }

    /**
     * @Author: Wls
     * @Date: 18:57 2019/9/7
     * @Description: 删除用户所有的绑定记录
     */
    @ApiOperation(value = "删除用户所有的绑定记录")
    @DeleteMapping("/user")
    public boolean deleteByUserOpenId(String userOpenId)
    {
        return userCompanyService.deleteByUserOpenId(userOpenId);
    }
}
