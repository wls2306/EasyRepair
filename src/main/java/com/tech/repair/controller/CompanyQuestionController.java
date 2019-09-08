package com.tech.repair.controller;

import com.tech.repair.po.CompanyQuestion;
import com.tech.repair.service.CompanyQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cq/")
@Api(tags = "单位自定义表单模块")
public class CompanyQuestionController {

    @Autowired
    private CompanyQuestionService companyQuestionService;

    /**
    * @Author: Wls
    * @Date: 0:50 2019/9/8
    * @Description: 新增 单位注册信息表单
     * ☆☆☆☆☆☆☆☆ 注意  ☆☆☆☆☆☆☆
     * 每个单位（companyId）仅允许有一条记录  Mysql报错：1062
     * 添加的companyId需要与 company表中的companyId一致！ Mysql报错：1452
     *
    */
    @PostMapping("/")
    @ApiOperation(value = "添加验证表单",notes = "★★ 注意！ 每个单位（companyId）仅允许有一条记录,添加的companyId需要与 company表中的companyId一致！★★")
    public CompanyQuestion addCompanyQuestion(CompanyQuestion cq)throws Exception
    {
        return companyQuestionService.addCompanyQuestion(cq);
    }

    /**
    * @Author: Wls
    * @Date: 0:56 2019/9/8
    * @Description: 更改注册信息验证表单
    */
    @PutMapping("/")
    @ApiOperation(value = "更改注册信息验证表单")
    public CompanyQuestion updateCompanyQuestion(CompanyQuestion cq)throws Exception
    {
        return companyQuestionService.updateCompanyQuestion(cq);
    }

    /**
    * @Author: Wls
    * @Date: 0:56 2019/9/8
    * @Description: 删除注册信息验证表单
    */
    @DeleteMapping("/")
    @ApiOperation(value = "删除注册信息验证表单")
    public boolean deleteCompanyQuestionByCompanyId(String companyId)
    {
        return companyQuestionService.deleteCompanyQuestion(companyId);
    }

    /**
    * @Author: Wls
    * @Date: 1:00 2019/9/8
    * @Description: 根据companyId 查询验证表单
    */
    @GetMapping("/")
    @ApiOperation(value = "查询验证表单")
    public CompanyQuestion selectCompanyQuestionByCompanyId(String companyId)
    {
        return companyQuestionService.selectCompanyQuestion(companyId);
    }

}
