package com.tech.repair.controller;

import com.tech.repair.po.RepairCompany;
import com.tech.repair.service.RepairCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "维修单位模块")
@RequestMapping("/api/rc")
public class RepairCompanyController  {
    @Autowired
    private RepairCompanyService repairCompanyService;

    /**
     * @Author:Wls
     * @Date:10:08 2019/9/25
     * @Description: 新增维修单位
     */
    @PostMapping("/")
    @ApiOperation(value = "新增维修单位")
    public RepairCompany addRepairCompany(RepairCompany rc)
    {
        return repairCompanyService.addRepairCompany(rc);
    }

    @GetMapping("/")
    @ApiOperation(value = "通过维修单位负责人邮箱查找维修单位")
    public RepairCompany getRepairCompany(String rcHost)
    {
        return repairCompanyService.getRepairComapnyByRcHost(rcHost);
    }

    @PutMapping("/")
    @ApiOperation(value = "更新维修单位信息",notes = "维修单位负责人邮箱rcHost必须填")
    public RepairCompany updateRepairCompany(RepairCompany rc)
    {
        return repairCompanyService.updateRepairCompany(rc);
    }

    @DeleteMapping("/")
    @ApiOperation(value = "删除维修单位通过维修单位Id")
    public boolean deleteRepairCompany(String id)
    {
        return repairCompanyService.deleteRcByRcId(id);
    }

}
