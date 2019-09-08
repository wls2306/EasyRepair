package com.tech.repair.service;

import com.tech.repair.po.Company;
import com.tech.repair.repository.CompanyRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class CompanyService {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private CompanyRepository companyRepository;


    public Company saveCompany(Company c)
    {
        c.setId(0);
        if (c!=null)
        {
            return companyRepository.save(c);
        }
        else
        {
            logger.warn("存储单位信息错误，参数错误！");
            return null;
        }
    }



    /**
     * @Author:Wls
     * @Date:19:11 2019/9/5
     * @Description: 查询单位信息 编号/全部
     */
    public Object getCompany(String companyId) {

        if (Strings.isNotBlank(companyId)) {
            switch (companyId) {
                case "all":
                    logger.info("查询所有单位");
                    return companyRepository.findAll();

                default:
                    logger.info("根据CompanyId 查询单位，CompanyId是{{}}" , companyId);
                    return companyRepository.findByCompanyId(companyId);

            }
        } else {
            logger.error("输入的CompanyId为空!");
            return null;
        }

    }

    /**
     * 更新单位信息
     * @param c
     * @return
     */
    public Company updateCompany(Company c)
    {
        logger.info("执行更新Company");
        if (c.getId()!=0)
            return companyRepository.save(c);
        else {
            return null;
        }
    }

    /**
     * 通过名称搜索单位
     */
    public List<Company> searchCompanyByName(String companyName) {
        if (Strings.isNotBlank(companyName)) {
            return companyRepository.findByCompanyNameLike(companyName);
        } else
        {
            logger.warn("search -参数错误！");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 0:07 2019/9/8
    * @Description: 删除单位
    */
    public boolean deleteCompanyByCompanyId(String companyId)
    {
        /**
         * 执行此操作后，数据库中所有与CompanyId 相同的数据均会被删除！
         */
        if (Strings.isNotBlank(companyId))
            return companyRepository.deleteByCompanyId(companyId)>0?true:false;
        else
        {
            logger.warn("delete- 参数错误");
            return false;
        }
    }






}
