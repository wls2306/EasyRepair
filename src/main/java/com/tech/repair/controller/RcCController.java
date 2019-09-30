package com.tech.repair.controller;

import com.tech.repair.po.RcC;
import com.tech.repair.service.RcApplyService;
import com.tech.repair.service.RcCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "单位-维修单位 关系模块")
@RequestMapping("/api/rcc")
public class RcCController {

    @Autowired
    private RcCService rcCService;

    /**
     * @Author:Wls
     * @Date:17:41 2019/9/25
     * @Description: 新增关系
     */
    @PostMapping("/")
    @ApiOperation(value = "添加关系")
    public RcC addRcc(RcC rcC)
    {
        return rcCService.addRcC(rcC);
    }

    /**
     * @Author:Wls
     * @Date:17:43 2019/9/25
     * @Description: 单位查
     */
    @ApiOperation(value = "单位查")
    @GetMapping("/company")
    public List<RcC> getByCompanyId(String companyId)
    {
        return rcCService.getRcCByCompanyId(companyId);
    }

    /**
     * @Author:Wls
     * @Date:17:44 2019/9/25
     * @Description: 维修单位查
     */
    @ApiOperation(value = "维修单位查")
    @GetMapping("/rc")
    public List<RcC> getByRcId(String rcId)
    {
        return rcCService.getRcCByRcId(rcId);
    }

    /**
     * @Author:Wls
     * @Date:17:52 2019/9/25
     * @Description: 删除
     */
    @DeleteMapping("/")
    @ApiOperation(value = "删除关系")
    public boolean deleteByCompanyIdAndRcId(String companyId,String rcId)
    {
        return rcCService.deleteByCompanyIdAndRcid(companyId, rcId);
    }

}
