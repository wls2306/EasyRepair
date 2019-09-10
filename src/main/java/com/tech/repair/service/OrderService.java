package com.tech.repair.service;

import com.tech.repair.po.Company;
import com.tech.repair.po.Order;
import com.tech.repair.po.User;
import com.tech.repair.repository.OrderRepository;
import com.tech.repair.vo.OrderVo;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    private final Logger logger= LoggerFactory.getLogger(getClass());

    public Order addOrder(Order o)
    {
        if (o!=null)
        {
            return orderRepository.save(o);
        }else
        {
            logger.warn("保存工单错误，对象为空");
            return null;
        }
    }


    public List<OrderVo> getOrderByCompanyId(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            List<OrderVo> result=new ArrayList<>();
            List<Order> orderList=orderRepository.findByOrderCompanyId(companyId);
            return doMix(orderList);
        }else
        {
            logger.warn("根据公司编号查询错误，参数错误！");
            return null;
        }
    }

    public List<OrderVo> getOrderByUserOpenId(String userOpenId)
    {
        if (Strings.isNotBlank(userOpenId)) {
            List<OrderVo> result=new ArrayList<>();
            List<Order> orderList=orderRepository.findByOrderUserOpenId(userOpenId);
            return doMix(orderList);
        }else {
            logger.warn("根据OpenId查询错误，参数错误！");
            return null;
        }
    }

    public Order updateOrder(Order o)
    {
        if (Strings.isNotBlank(o.getOrderId()))
        {
            return orderRepository.save(o);
        }else
        {
            logger.warn("更新order,参数为空");
            return null;
        }
    }


    private List<OrderVo> doMix(List<Order> orderList)
    {
        List<OrderVo> result=new ArrayList<>();
       if (orderList!=null) {
           for (Order order : orderList) {
               User u = (User) userService.getUser(order.getOrderUserOpenId());
               Company c = (Company) companyService.getCompany(order.getOrderCompanyId());
               OrderVo orderVo = new OrderVo();
               BeanUtils.copyProperties(u, orderVo);
               BeanUtils.copyProperties(c, orderVo);
               BeanUtils.copyProperties(order, orderVo);
               result.add(orderVo);
           }
           return result;
       }else
       {
           logger.warn("组装错误，orderList为空！");
           return null;
       }
    }



}
