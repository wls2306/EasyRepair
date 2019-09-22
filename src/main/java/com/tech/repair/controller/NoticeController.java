package com.tech.repair.controller;

import com.tech.repair.po.Notice;
import com.tech.repair.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
@Api(tags = "公告模块")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "添加通知")
    @PostMapping("/")
    public Notice postNotice(Notice n)
    {
        return noticeService.saveNotice(n);
    }

    @ApiOperation(value = "获取单位通知")
    @GetMapping("/company")
    public List<Notice> getNoticeByCompanyId(String companyId)
    {
        return noticeService.findNoticeByCompanyId(companyId);
    }

    @ApiOperation(value = "获取公共通知")
    @GetMapping("/public")
    public List<Notice> getPublicNotice()
    {
        return noticeService.findPublicNotice();
    }

    @ApiOperation(value = "根据Id删除公告")
    @DeleteMapping("/")
    public boolean deleteNoticeById(String id)
    {
        return noticeService.deleteNoticeById(id);
    }

}
