package com.tech.repair.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.repair.pojo.Company;
import com.tech.repair.pojo.User;
import com.tech.repair.pojo.UserCompany;
import com.tech.repair.repository.UserCompanyRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 进行删除操作时，需要带@Transactional事物注解
 */
@Transactional
@Service
public class UserCompanyService {

    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    /**
     * @Author: Wls
     * @Date: 18:10 2019/9/7
     * @Description: 新建 用户-单位 关系
     */
    public UserCompany addUserCompany(UserCompany userCompany)
    {

        if (Strings.isNotBlank(userCompany.getCompanyId())&&Strings.isNotBlank(userCompany.getUserOpenId())) {
            ObjectMapper mapper=new ObjectMapper();
            return (UserCompany)userCompanyRepository.save(userCompany);
        }
        else
        {
            logger.warn("新建 用户-单位:参数错误");
            return null;
        }
    }

    /**
     * @Author: Wls
     * @Date: 18:12 2019/9/7
     * @Description: 获取所有单位下的用户信息
     */
    public List<User> getUserByCompanyId(String companyId)
    {
        if (Strings.isNotBlank(companyId)) {
            List<UserCompany> list = userCompanyRepository.findByCompanyId(companyId);
            List<User> userList = new ArrayList<>();
            for (UserCompany userCompany : list) {
                User u = (User) userService.getUser(userCompany.getUserOpenId());
                userList.add(u);
            }
            return userList;
        }else
        {
            logger.warn("参数错误");
            return null;
        }
    }


    /**
     * @Author: Wls
     * @Date: 18:22 2019/9/7
     * @Description: 获取所有用户名下的单位信息
     */
    public List<Company> getCompanyByUserOpenId(String userOpenId)
    {

        if (Strings.isNotBlank(userOpenId)) {
            List<UserCompany> list = userCompanyRepository.findByUserOpenId(userOpenId);
            List<Company> companyList = new ArrayList<>();
            for (UserCompany userCompany : list) {
                Company c = (Company) companyService.getCompany(userCompany.getCompanyId());
                companyList.add(c);
            }
            ObjectMapper mapper = new ObjectMapper();
            return companyList;
        }else
        {
            logger.warn("参数错误");
            return null;
        }
    }

    /**
     * @Author: Wls
     * @Date: 18:24 2019/9/7
     * @Description: 根据用户OpenId和CompanyId 删除 用户-单位 记录，即实现 “解约”功能
     */
    public boolean deleteByUserOpenIdAndCompanyId(String userOpenId,String companyId)
    {

        if (Strings.isNotBlank(userOpenId)&&Strings.isNotBlank(companyId))
        {
            return userCompanyRepository.deleteByCompanyIdAndAndUserOpenId(companyId,userOpenId)>0?true:false;
        }else
        {
            logger.warn("参数错误");
            return false;
        }
    }

    /**
    * @Author: Wls
    * @Date: 18:39 2019/9/7
    * @Description:根据单位编号删除单位下属的所有 用户-单位 关系
    */
    public boolean deleteByCompanyId(String companyId)
    {
        if (Strings.isNotEmpty(companyId))
        {
            return userCompanyRepository.deleteByCompanyId(companyId)>0?true:false;
        }else
        {
            logger.warn("参数错误");
            return false;
        }
    }

    /**
    * @Author: Wls
    * @Date: 18:41 2019/9/7
    * @Description: 根据UserOpenId， 删除用户所属的所有单位
    */
    public boolean deleteByUserOpenId(String userOpenId)
    {
        if (Strings.isNotEmpty(userOpenId))
        {
            return userCompanyRepository.deleteByUserOpenId(userOpenId)>0;
        }else
        {
            logger.warn("参数错误");
            return false;
        }
    }




}
