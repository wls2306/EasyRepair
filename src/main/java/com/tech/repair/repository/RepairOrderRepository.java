package com.tech.repair.repository;

import com.tech.repair.po.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder,Integer> {

    RepairOrder findByOrderId(String orderId);

    List<RepairOrder> findByOrderCompanyId(String companyId);

    List<RepairOrder> findByOrderUserOpenId(String openId);

    RepairOrder findByOrderUserOpenIdAndOrderCompanyId(String userOpenId, String companyId);

}
