package com.tech.repair.controller;

import com.tech.repair.po.RcApply;
import com.tech.repair.service.RcApplyService;
import com.tech.repair.vo.RcVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "合作关系申请模块")
@RequestMapping("/api/rca/")
public class RcApplyController {
    @Autowired
    private RcApplyService rcApplyService;


    @PostMapping("/")
    @ApiOperation(value = "添加合作关系申请")
    public RcApply addRcApply(RcApply rcApply)
    {
        return rcApplyService.addRcApply(rcApply);
    }


    @GetMapping("/")
    @ApiOperation(value = "根据单位编号和维修单位编号获取申请")
    public RcVo getApplyByCompanyIdAndRcId(String companyId,String rcId)
    {
        return rcApplyService.getRcApplyByRcIdAndCompanyId(rcId, companyId);
    }


    @GetMapping("/company")
    @ApiOperation(value = "根据单位查询关系申请")
    public List<RcVo> getRcApplyByCompanyId(String companyId)
    {
        return rcApplyService.getRcApplyByCompanyId(companyId);
    }

    @GetMapping("/rc")
    @ApiOperation(value = "根据维修单位查询关系申请")
    public List<RcVo> getRcApplyByRcid(String rcid)
    {
        return rcApplyService.getRcApplyByRcId(rcid);
    }

    @PutMapping("/pass")
    @ApiOperation(value = "根据申请编号通过申请")
    public boolean passedApply(String id)
    {
        //TODO 还需要添加通过后的绑定逻辑！！
        return rcApplyService.passedRcApply(id);
    }

    @PutMapping("/reject")
    @ApiOperation(value = "根据申请编号拒绝申请")
    public boolean rejectApply(String id)
    {
        return rcApplyService.rejectRcAplly(id);
    }

    @DeleteMapping("/")
    @ApiOperation(value = "根据申请编号删除申请")
    public boolean deleteById(String id)
    {
        return rcApplyService.deleteRcApply(id);
    }

}
