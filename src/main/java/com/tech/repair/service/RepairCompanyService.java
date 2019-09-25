package com.tech.repair.service;

import cn.hutool.core.util.RandomUtil;
import com.tech.repair.po.RepairCompany;
import com.tech.repair.repository.RepairCompanyRepository;
import com.tech.repair.util.getNullPropertyNames;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RepairCompanyService {

    private Logger logger= LoggerFactory.getLogger(getClass());


    @Autowired
    private RepairCompanyRepository repairCompanyRepository;

    /**
     * @Author:Wls
     * @Date:9:29 2019/9/25
     * @Description: 添加维修单位
     */
    public RepairCompany addRepairCompany(RepairCompany rc)
    {
        if (Strings.isNotBlank(rc.getRcHost())) {
            SimpleDateFormat sdf=new SimpleDateFormat();
            String rcId=sdf.format(new Date())+ RandomUtil.randomInt(1000,9999);
            rc.setRcId(rcId);
            logger.info("随机分配的维修单位编号："+rcId);
            return repairCompanyRepository.save(rc);
        }else
        {
            logger.warn("添加维修单位 参数错误");
            return null;
        }
    }

    /**
     * @Author:Wls
     * @Date:9:54 2019/9/25
     * @Description: 获取维修单位
     */

    public RepairCompany getRepairComapnyByRcHost(String rcHost)
    {
        if (Strings.isNotBlank(rcHost)) {
            return repairCompanyRepository.findByRcHost(rcHost);
        }else
        {
            logger.warn("获取维修单位 参数错误");
            return null;
        }
    }



    /**
     * @Author:Wls
     * @Date:9:40 2019/9/25
     * @Description: 删除维修单位
     */
    public boolean deleteRcByRcId(String RcId)
    {
        if (Strings.isNotBlank(RcId)) {
            return repairCompanyRepository.deleteByRcId(RcId) > 0;
        }else
        {
            logger.warn("删除维修单位 参数错误");
            return false;
        }
    }


    /**
     * @Author:Wls
     * @Date:9:45 2019/9/25
     * @Description:
     */
    public RepairCompany updateRepairCompany(RepairCompany rc)
    {
        if (Strings.isNotBlank(rc.getRcHost())) {
            RepairCompany target=getRepairComapnyByRcHost(rc.getRcHost());
            BeanUtils.copyProperties(rc,target, getNullPropertyNames.getNullPropertyNames(rc));
            return repairCompanyRepository.save(target);
        }else
        {
            logger.warn("更新维修单位-参数错误");
            return null;
        }
    }


}
