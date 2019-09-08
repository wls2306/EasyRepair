package com.tech.repair.controller;

import com.tech.repair.po.System;
import com.tech.repair.service.SystemService;
import com.tech.repair.util.UploadPathUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/api/sys/")
@Api(tags = "系统设置模块（轮播图）")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping("/")
    @ApiOperation(value = "获取当前系统信息（轮播图）")
    public System getSystemInfo()
    {
        return systemService.getSystemInfo();
    }

    /**
    * @Author: Wls
    * @Date: 13:37 2019/9/8
    * @Description: 代码详细介绍 见 CompanyController
    */
    @PutMapping("/")
    @ApiOperation(value = "更新系统设置")
    public System updateSystem(HttpServletRequest req, System system, MultipartFile[] files)throws Exception
    {
        if (files!=null)
        {
            String imageFile="";
            for (int i=0;i<files.length;i++) {
                MultipartFile file=files[i];
                String filePath= req.getSession().getServletContext().getRealPath("/")+"images\\system\\"+i+".jpg";
                filePath= UploadPathUtil.getAbsolutePath(filePath);
                File file1=new File(filePath);
                if (!file1.exists()) {
                    file1.mkdirs();
                }
                file.transferTo(file1);
                imageFile+="image/system/"+i+".jpg;";

            }
            system.setSystemImages(imageFile);
            return systemService.updateSystemInfo(system);

        }else {
            return systemService.updateSystemInfo(system);
        }
    }



}
