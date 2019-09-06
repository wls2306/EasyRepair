package com.tech.repair.service;

import com.tech.repair.pojo.Company;
import com.tech.repair.repository.CompanyRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyRepository companyRepository;

    public Object getCompany(String companyId) {
        /**
         * @Author:Wls
         * @Date:19:11 2019/9/5
         * @Description: 获取单位编号
         */
        if (Strings.isNotEmpty(companyId)) {
            switch (companyId) {
                case "all":
                    logger.info("查询所有单位");
                    return companyRepository.findAll();

                default:
                    logger.info("根据CompanyId 查询单位，CompanyId是" + companyId);
                    return companyRepository.findByCompanyId(companyId);

            }
        } else {
            logger.error("输入的CompanyId为空!");
            return null;
        }

    }
}
