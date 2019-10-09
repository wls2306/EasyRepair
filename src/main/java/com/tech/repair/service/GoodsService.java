package com.tech.repair.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.repair.po.Goods;
import com.tech.repair.repository.GoodsRepository;
import com.tech.repair.util.getNullPropertyNames;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    private Logger logger=LoggerFactory.getLogger(getClass());

    /**
    * @Author: Wls
    * @Date: 20:01 2019/10/5
    * @Description: 添加物品
    */
    public Goods addGood(Goods g)throws Exception
    {
        if (Strings.isNotBlank(g.getGoodsCompanyId())&&Strings.isNotBlank(g.getGoodsId())) {
            ObjectMapper mapper=new ObjectMapper();
            logger.info("新增物品：{{}}",mapper.writeValueAsString(g));
            return goodsRepository.save(g);
        }else {
            logger.warn("添加物品 参数错误");
            return null;
        }
    }

    /**
     * @Author: Wls
     * @Date: 20:13 2019/10/5
     * @Description: 更新物品信息
     */
    public Goods updateGoods(Goods g)
    {
        if (Strings.isNotBlank(g.getGoodsId()+"")) {
            Goods oldGoods=goodsRepository.findAllByGoodsId(g.getGoodsId());
            BeanUtils.copyProperties(g,oldGoods, getNullPropertyNames.getNullPropertyNames(g));
            return goodsRepository.save(oldGoods);
        }else {
            logger.warn("更新物品 参数错误");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 20:05 2019/10/5
    * @Description: 根据物品编号查询物品
    */
    public Goods findGoodsById(String id)
    {
        if (Strings.isNotBlank(id)) {
            return goodsRepository.findAllByGoodsId(id);
        }else
        {
            logger.warn("根据id 查找物品：参数错误");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 20:11 2019/10/5
    * @Description: 根据单位编号查询物品
    */
    public List<Goods> findGoodsByCompanyId(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            return goodsRepository.findAllByGoodsCompanyId(companyId);
        }else{
            logger.warn("根据公司编号查询商品 -参数错误");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 20:20 2019/10/5
    * @Description: 删除物品
    */
    public boolean deleteGoodsById(String id){
        if (Strings.isNotBlank(id)) {
            return goodsRepository.deleteByGoodsId(id)>0;
        }else {
            logger.warn("删除物品-参数错误");
            return false;
        }
    }





}
