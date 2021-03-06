package com.tech.repair.service;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.repair.po.User;
import com.tech.repair.repository.UserRepository;
import com.tech.repair.util.getNullPropertyNames;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional
@Service
@Configuration
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Value("${wx.appid}")
    private  String APPID;

    @Value("${wx.secret}")
    private  String APPSECRET;

    public String wxLogin(String code){
        /**
         * @Author:Wls
         * @Date:19:50 2019/9/5
         * @Description:  微信登录
         */
        if (Strings.isNotBlank(code)) {
            Map loginMap=new HashMap<String,String>();
            loginMap.put("appid",APPID);
            loginMap.put("secret",APPSECRET);
            loginMap.put("js_code",code);
            loginMap.put("grant_type","authorization_code");
            logger.info("开始执行登录，AppId="+APPID+",Secret="+APPSECRET+" ,Code="+code);
            String result = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session?")
                    .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                    .form(loginMap)//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            logger.info("登录返回结果：{{}}",result);
            return result;
        }
        else {
            logger.warn("微信登录 - code为空");
            return "";
        }

    }


    public Object getUser(String userOpenId) {
        /**
         * @Author:Wls
         * @Date:19:11 2019/9/5
         * @Description: 获取用户
         */
        if (Strings.isNotBlank(userOpenId)) {
            switch (userOpenId) {
                case "all":
                    logger.info("查询所有用户");
                    return userRepository.findAll();

                default:
                    logger.info("根据OpenId 查询用户，OpenId是{{}}" , userOpenId);
                    return userRepository.findByUserOpenId(userOpenId);

            }
        } else {
            logger.error("获取用户-输入的UserOpenId为空!");
            return null;
        }

    }

    /**
     * @Author:Wls
     * @Date:8:44 2019/9/25
     * @Description: 根据邮箱、密码获取用户
     */
    public User getUserByEmail(String userEmail)
    {
        if (Strings.isNotBlank(userEmail)) {
            return userRepository.findByUserEmail(userEmail);
        }else {
            logger.error("获取用户-输入的Email为空!");
            return null;
        }
    }



    public Object addUser(User user)throws Exception
    {
        /**
         * @Author:Wls
         * @Date:12:36 2019/9/6
         * @Description: 添加新用户
         */

            ObjectMapper mapper=new ObjectMapper();
            logger.info("添加新用户："+mapper.writeValueAsString(user));
           return userRepository.save(user);

    }

    public boolean updateUserCompanyByUserOpenId(String companyId,String openId)
    {
        /**
         * @Author:Wls
         * @Date:12:33 2019/9/6
         * @Description: 修改用户选择的单位id
         */
        if (Strings.isNotBlank(companyId)&&Strings.isNotBlank(openId))
            return userRepository.updateUserCompanyByUserOpenId(companyId,openId)>0?true:false;
        else {
            logger.error("修改用户选择的单位id-参数错误");
            return false;
        }

    }

    public boolean updateUserRoleByUserOpenId(String userRole,String userOpenId)
    {
        /**
         * @Author:Wls
         * @Date:12:32 2019/9/6
         * @Description:  修改用户角色
         */
        if (Strings.isNotBlank(userRole)&&Strings.isNotBlank(userOpenId))
            return userRepository.updateUserRoleByUserOpenId(userRole,userOpenId)>0?true:false;
        else {
            logger.error("修改用户角色-参数错误");
            return false;
        }
    }


    public boolean updateUserPhoneByUserOpenId(String userPhone,String userOpenId)
    {
        /**
         * @Author:Wls
         * @Date:12:32 2019/9/6
         * @Description: 修改用户联系方式
         */
        if (Strings.isNotBlank(userPhone)&&Strings.isNotBlank(userOpenId))
            return userRepository.updateUserRoleByUserOpenId(userPhone,userOpenId)>0?true:false;
        else {
            logger.error("修改用户联系方式-参数错误");
            return false;
        }
    }


  /*  public boolean updateUserStatusByUserOpenId(String userStatus,String userOpenId)
    {
        *//**
         * @Author:Wls
         * @Date:12:32 2019/9/6
         * @Description: 修改用户状态
         *//*
        if (Strings.isNotBlank(userStatus)&&Strings.isNotBlank(userOpenId))
            return userRepository.updateUserStatusByUserOpenId(userStatus,userOpenId)>0?true:false;
        else {
            logger.error("修改用户状态-参数错误");
            return false;
        }
    }*/

    public User updateUser(User u)throws Exception
    {
        /**
         * @Author:Wls
         * @Date:13:20 2019/9/6
         * @Description: 更新用户
         */
        logger.info("更新用户信息");

        if (Strings.isNotBlank(u.getUserOpenId())||Strings.isNotBlank(u.getUserEmail())) {
            /**
            * @Author: Wls
            * @Date: 10:44 2019/10/6
            * @Description: 根据Email或OpenId任意一项修改用户信息
            */
            User oldUser=Strings.isBlank(u.getUserOpenId())?getUserByEmail(u.getUserEmail()):(User)getUser(u.getUserOpenId());
            BeanUtils.copyProperties(u,oldUser, getNullPropertyNames.getNullPropertyNames(u));
            ObjectMapper mapper=new ObjectMapper();
            System.out.println(mapper.writeValueAsString(oldUser));
            return userRepository.save(oldUser);
        }else {
            logger.error("更新用户信息-参数错误");
            return null;
        }

        /*if (u!=null)
        {
            ObjectMapper mapper=new ObjectMapper();
            logger.info("更新用户信息："+mapper.writeValueAsString(u));
           return(User) userRepository.save(u);
        }else {
            logger.error("更新用户信息-参数错误");
            return null;
        }*/
    }

    /**
    * @Author: Wls
    * @Date: 0:25 2019/9/8
    * @Description: 删除用户，执行此操作后，数据库中所有与 UserOpenId 相同的数据均会被删除！
    */
    public boolean deleteByUserOpenId(String userOpenId)
    {
        if (Strings.isNotBlank(userOpenId))
            return userRepository.deleteByUserOpenId(userOpenId) > 0;
        else
        {
            logger.warn("删除用户- 参数错误");
            return false;
        }
    }


    public User findUserByEmailAndPwd(String userEmail,String pwd)
    {
        if (Strings.isNotBlank(userEmail)&&Strings.isNotBlank(pwd)) {
            return userRepository.findByUserEmailAndUserPwd(userEmail, pwd);
        } else
        {
            logger.warn("用户邮箱登录-参数为空");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 10:40 2019/10/6
    * @Description: 根据单位查找用户
    */
    public List<User> findUserByCompanyId(String userCompanyId){
        if (Strings.isNotBlank(userCompanyId)) {
            return userRepository.findAllByUserBelong(userCompanyId);
        }else {
            logger.warn("根据单位查找用户-参数错误");
            return null;
        }
    }


    /**
     * @Author:Wls
     * @Date:19:28 2019/10/9
     * @Description: 根据用户权限查询用户
     */
    public List<User> findByUserRole(String userRole)
    {
        if (Strings.isNotBlank(userRole)) {
            return userRepository.findByUserRole(userRole);
        }else {
            logger.warn("根据用户权限查询用户-参数错误");
            return null;
        }
    }

    /**
    * @Author: Wls
    * @Date: 11:02 2019/10/7
    * @Description: 根据邮箱删除用户
    */
    public boolean deleteUserByUserEmail(String userEmail){
        if (Strings.isNotBlank(userEmail)) {
            return userRepository.deleteByUserEmail(userEmail) > 0;
        }else {
            logger.warn("根据邮箱删除用户-参数错误");
            return false;
        }
    }


}
