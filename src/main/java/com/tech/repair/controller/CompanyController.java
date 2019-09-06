package com.tech.repair.controller;

import com.tech.repair.pojo.Company;
import com.tech.repair.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "单位模块")
@RestController
@RequestMapping("/api/company/")
public class CompanyController {

    private final Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    @ApiOperation(value ="获取所有单位记录" )
    public List<Company> getAllCompany()
    {
        /**
         * @Author:Wls
         * @Date:14:59 2019/9/6
         * @Description: 获取所有单位
         */

        return  (List<Company>) companyService.getCompany("all");
    }

    @GetMapping("/{companyId}")
    @ApiOperation(value ="根据CompanyId单位记录" )
    public Company getCompanyByCompanyId(@PathVariable("companyId")String companyId)
    {
        /**
         * @Author:Wls
         * @Date:15:13 2019/9/6
         * @Description: 根据编号获取单位信息
         */

        return (Company) companyService.getCompany(companyId);
    }

    @PutMapping("/")
    @ApiOperation(value = "更新单位信息")
    public Company updateCompanyByCompanyId(Company company)
    {
        /**
         * @Author:Wls
         * @Date:15:22 2019/9/6
         * @Description: 更新单位信息
         */
        return (Company) companyService.
    }

}
