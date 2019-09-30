package com.tech.repair.controller;

import cn.hutool.core.util.RandomUtil;
import com.tech.repair.po.RepairCompany;
import com.tech.repair.service.RepairCompanyService;
import com.tech.repair.util.UploadPathUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public RepairCompany addRepairCompany(RepairCompany rc, MultipartFile[] files)throws Exception
    {
        SimpleDateFormat sdf=new SimpleDateFormat();
        String rcId=sdf.format(new Date())+ RandomUtil.randomInt(1000,9999);
        rc.setRcId(rcId);

        String imgUrls="";
        if (files!=null) {
            String path= ClassUtils.getDefaultClassLoader().getResource("").getPath();
            path+="static\\images\\repair_company\\";
            int i=0;
            for (MultipartFile file : files) {
                String filePath= UploadPathUtil.getAbsolutePath(path);
                File rs=new File(path,i+".jpg");
                if (!rs.exists())
                {
                    rs.mkdirs();
                }
                file.transferTo(rs);
                imgUrls+="/images/repair_company/"+i+".jpg;";
                i++;
            }
            rc.setRcImage(imgUrls);
        }

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
