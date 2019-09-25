package com.tech.repair.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tech.repair.po.Company;
import com.tech.repair.po.RepairOrder;
import com.tech.repair.po.User;
import com.tech.repair.repository.RepairOrderRepository;
import com.tech.repair.util.getNullPropertyNames;
import com.tech.repair.vo.OrderVo;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RepairOrderService {
    @Autowired
    private RepairOrderRepository repairOrderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    private final Logger logger= LoggerFactory.getLogger(getClass());

    /**
     * @Author:Wls
     * @Date:8:29 2019/9/11
     * @Description:
     */
    public RepairOrder addOrder(RepairOrder o)
    {
        if (Strings.isNotBlank(o.getOrderUserOpenId()))
        {
            logger.info("保存工单中...");
            o.setOrderId("R"+o.getOrderCompanyId()+ RandomUtil.randomInt(10000,99999));
            o.setOrderStatus("0");
            JSONObject  jsonObject=  JSONUtil.parseObj(o);
            logger.info(jsonObject.toString());
            return repairOrderRepository.save(o);
        }else
        {
            logger.warn("保存工单错误，对象为空");
            return null;
        }
    }

    /**
     * @Author:Wls
     * @Date:8:29 2019/9/11
     * @Description:
     */
    public List<OrderVo> getOrderByCompanyId(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            List<OrderVo> result=new ArrayList<>();
            List<RepairOrder> repairOrderList = repairOrderRepository.findByOrderCompanyId(companyId);
            return doMix(repairOrderList);
        }else
        {
            logger.warn("根据公司编号查询错误，参数错误！");
            return null;
        }
    }


    /**
     * @Author:Wls
     * @Date:8:29 2019/9/11
     * @Description:
     */
    public List<OrderVo> getOrderByUserOpenId(String userOpenId)
    {
        if (Strings.isNotBlank(userOpenId)) {
            List<OrderVo> result=new ArrayList<>();
            List<RepairOrder> repairOrderList = repairOrderRepository.findByOrderUserOpenId(userOpenId);
            return doMix(repairOrderList);
        }else {
            logger.warn("根据OpenId查询错误，参数错误！");
            return null;
        }
    }
    /**
     * @Author:Wls
     * @Date:8:29 2019/9/11
     * @Description:
     */
    public OrderVo getOrderByOpenIdAndCompanyId(String companyId,String userOpenId)
    {
        RepairOrder o= repairOrderRepository.findByOrderUserOpenIdAndOrderCompanyId(userOpenId,companyId);
        return doMix(o);
    }


    /**
     * @Author:Wls
     * @Date:8:29 2019/9/11
     * @Description:
     */
    public RepairOrder updateOrder(RepairOrder o)
    {
        if (Strings.isNotBlank(o.getOrderId()))
        {
            RepairOrder target= repairOrderRepository.findByOrderId(o.getOrderId());
            BeanUtils.copyProperties(o,target, getNullPropertyNames.getNullPropertyNames(o));
            return repairOrderRepository.save(target);
        }else
        {
            logger.warn("更新order,orderId参数为空");
            return null;
        }
    }

    /**
     * @Author:Wls
     * @Date:8:28 2019/9/11
     * @Description:
     */
    public OrderVo getOrderByOrderId(String orderId)
    {
        if (Strings.isNotBlank(orderId)) {
            return doMix(repairOrderRepository.findByOrderId(orderId));
        }else
        {
            logger.warn("查询失败,orderId参数为空");
            return null;
        }


    }


    /**
     * @Author:Wls
     * @Date:8:29 2019/9/11
     * @Description:
     */
    private List<OrderVo> doMix(List<RepairOrder> repairOrderList)
    {
        List<OrderVo> result=new ArrayList<>();
       if (repairOrderList !=null) {
           for (RepairOrder repairOrder : repairOrderList) {
               User u = (User) userService.getUser(repairOrder.getOrderUserOpenId());
               Company c = (Company) companyService.getCompany(repairOrder.getOrderCompanyId());
               OrderVo orderVo = new OrderVo();
               BeanUtils.copyProperties(u, orderVo);
               BeanUtils.copyProperties(c, orderVo);
               BeanUtils.copyProperties(repairOrder, orderVo);
               result.add(orderVo);
           }
           return result;
       }else
       {
           logger.warn("组装错误，orderList为空！");
           return null;
       }

    }

    /**
     * @Author:Wls
     * @Date:8:29 2019/9/11
     * @Description:
     */
    private OrderVo doMix(RepairOrder repairOrder) {

        if (Strings.isNotBlank(repairOrder.getOrderId())) {
            OrderVo result = new OrderVo();
            User u = (User) userService.getUser(repairOrder.getOrderUserOpenId());
            Company c = (Company) companyService.getCompany(repairOrder.getOrderCompanyId());
            BeanUtils.copyProperties(u, result);
            BeanUtils.copyProperties(c, result);
            BeanUtils.copyProperties(repairOrder, result);
            return result;
        } else {
            logger.warn("组装错误，orderId为空！");
            return null;
        }
    }
}
