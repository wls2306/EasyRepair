package com.tech.repair.repository;

import com.tech.repair.po.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findByOrderId(String orderId);

    List<Order> findByOrderCompanyId(String companyId);

    List<Order> findByOrderUserOpenId(String openId);



}
