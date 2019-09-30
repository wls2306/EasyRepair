package com.tech.repair.service;

import com.tech.repair.po.RcC;
import com.tech.repair.repository.RcCRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RcCService {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RcCRepository rcCRepository;

    /**
     * @Author:Wls
     * @Date:17:29 2019/9/25
     * @Description: 增加单位-维修单位合作关系
     */
    public RcC addRcC(RcC r)
    {
        if (Strings.isNotBlank(r.getCompanyId())&&Strings.isNotBlank(r.getRcId())) {
            return rcCRepository.save(r);
        }else
        {
            logger.warn("addRcC-参数错误");
            return null;
        }
    }

    /**
     * @Author:Wls
     * @Date:17:38 2019/9/25
     * @Description:
     */
    public List<RcC> getRcCByCompanyId(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            return rcCRepository.findByCompanyId(companyId);
        }else{
            logger.warn("getRcCByCompanyId-参数错误");
            return null;
        }
    }
    /**
     * @Author:Wls
     * @Date:17:38 2019/9/25
     * @Description:
     */
    public List<RcC> getRcCByRcId(String rcId)
    {
        if (Strings.isNotBlank(rcId)) {
            return rcCRepository.findByRcId(rcId);
        }else{
            logger.warn("getRcCByRcId-参数错误");
            return null;
        }
    }

    /**
     * @Author:Wls
     * @Date:17:38 2019/9/25
     * @Description: 删除
     */
    public boolean deleteByCompanyIdAndRcid(String companyId,String rcId)
    {
        if (Strings.isNotBlank(companyId)&&Strings.isNotBlank(rcId)) {
            return rcCRepository.deleteByRcIdAndCompanyId(rcId, companyId)>0;
        }else {
            logger.warn("deleteByCompanyIdAndRcid-参数错误");
            return false;
        }
    }


}
