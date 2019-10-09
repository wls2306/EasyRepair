package com.tech.repair.controller;

import com.tech.repair.po.Goods;
import com.tech.repair.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "物品模块")
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加物品")
    @PostMapping("/")
    public Goods addGoods(Goods g)throws Exception
    {
        return goodsService.addGood(g);
    }

    @ApiOperation(value = "根据编号查找物品")
    @GetMapping("/")
    public Goods getGoodsById(String id){
        return goodsService.findGoodsById(id);
    }

    @ApiOperation(value = "根据单位编号查询单位内的物品清单")
    @GetMapping("/company")
    public List<Goods> getGoodsByCompanyId(String companyId)
    {
        return goodsService.findGoodsByCompanyId(companyId);
    }

    @ApiOperation(value = "更新物品信息",notes = "不更新项留空，物品编号(GoodsId)必填")
    @PutMapping("/")
    public Goods updateGoods(Goods g){
        return goodsService.updateGoods(g);
    }

    @ApiOperation(value = "删除物品")
    @DeleteMapping("/")
    public boolean deleteGoods(String goodsId){
        return goodsService.deleteGoodsById(goodsId);
    }
}
