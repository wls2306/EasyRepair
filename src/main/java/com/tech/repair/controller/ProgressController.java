package com.tech.repair.controller;

import com.tech.repair.po.Progress;
import com.tech.repair.service.ProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pro")
@Api(tags = "工单流程模块")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    /**
    * @Author: Wls
    * @Date: 13:37 2019/9/14
    * @Description: 新增工单流程
    */
    @PostMapping("/")
    @ApiOperation(value = "新建工单流程",notes = "★★ 请确认 progressOrderId 与 Order表中的orderId  一致！ 不然 Err 1064★★")
    public Progress addProgress(Progress p)throws Exception
    {
        return progressService.addProgress(p);
    }


    /**
    * @Author: Wls
    * @Date: 13:37 2019/9/14
    * @Description: 根据工单号查询所有流程
    */
    @GetMapping("/")
    @ApiOperation(value = "根据工单号查询所有流程",notes = "返回对象为 List<Progress> 列表")
    public List<Progress> getProgressByOrderId(String orderId)
    {
        return progressService.getProgressById(orderId);
    }



}
