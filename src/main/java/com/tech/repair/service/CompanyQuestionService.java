package com.tech.repair.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.repair.po.CompanyQuestion;
import com.tech.repair.repository.CompanyQuestionRepository;
import com.tech.repair.util.getNullPropertyNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyQuestionService {

    final private Logger logger=LogManager.getLogger(getClass());

    @Autowired
    private CompanyQuestionRepository companyQuestionRepository;

    /**
    * @Author: Wls
    * @Date: 1:12 2019/9/8
    * @Description: 增添记录
    */
    public CompanyQuestion addCompanyQuestion(CompanyQuestion cq)throws Exception
    {
        if (Strings.isNotBlank(cq.getCompanyId())) {
            ObjectMapper mapper=new ObjectMapper();
            logger.info("add -{{}}",mapper.writeValueAsString(cq));
            return (CompanyQuestion) companyQuestionRepository.save(cq);
        }else
        {
            logger.warn("add- 参数错误");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 1:12 2019/9/8
    * @Description: 更改
    */
    public CompanyQuestion updateCompanyQuestion(CompanyQuestion cq)throws Exception
    {
        CompanyQuestion target=companyQuestionRepository.findByCompanyId(cq.getCompanyId());
        if (Strings.isNotBlank(cq.getCompanyId())) {
            ObjectMapper mapper=new ObjectMapper();
            logger.info("update -{{}}",mapper.writeValueAsString(cq));
            BeanUtils.copyProperties(cq,target, getNullPropertyNames.getNullPropertyNames(cq));
            return (CompanyQuestion) companyQuestionRepository.save(target);
        }else {
            logger.warn("update -参数错误");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 1:13 2019/9/8
    * @Description: 查询
    */

    public CompanyQuestion selectCompanyQuestion(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            return companyQuestionRepository.findByCompanyId(companyId);
        }else
        {
            logger.warn( "select -参数错误");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 1:14 2019/9/8
    * @Description: 删除记录
    */

    public boolean deleteCompanyQuestion(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            return companyQuestionRepository.deleteByCompanyId(companyId) > 0;
        }else
        {
            logger.warn("delete -参数错误");
            return false;
        }
    }


}
