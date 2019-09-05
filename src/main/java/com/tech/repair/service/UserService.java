package com.tech.repair.service;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.tech.repair.pojo.User;
import com.tech.repair.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Value("${wx.appid}")
    private static String APPID;

    @Value("${wx.secret}")
    private static String APPSECRET;

    public String wxLogin(String code){
        /**
         * @Author:Wls
         * @Date:19:50 2019/9/5
         * @Description:  微信登录模块
         */
        Map loginMap=new HashMap<String,String>();
        loginMap.put("appid",APPID);
        loginMap.put("secret",APPSECRET);
        loginMap.put("js_code",code);
        loginMap.put("grant_type","authorization_code");

        String result = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session?")
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .form(loginMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        logger.info("登录返回结果："+result);
        return result;

    }


    public Object getUser(String userOpenId) {
        /**
         * @Author:Wls
         * @Date:19:11 2019/9/5
         * @Description: 获取用户
         */
        if (Strings.isNotEmpty(userOpenId)) {
            switch (userOpenId) {
                case "all":
                    logger.info("查询所有用户");
                    return userRepository.findAll();

                default:
                    logger.info("根据OpenId 查询用户，OpenId是" + userOpenId);
                    return userRepository.findByUserOpenId(userOpenId);

            }
        } else {
            logger.error("输入的UserOpenId为空!");
            return null;
        }

    }

    public Object addUser(User user)
    {
        if (user!=null)
        {
           return userRepository.save(user);
        }else {
            logger.error("用户对象为空！");
            return null;
        }
    }

    public boolean updateUserCompanyByUserOpenId(String companyId,String openId)
    {
        return userRepository.updateUserCompanyByUserOpenId(companyId,openId)>0?true:false;
    }

    public boolean updateUserRoleByUserOpenId(String userRole,String userOpenId)
    {
        return userRepository.updateUserRoleByUserOpenId(userRole,userOpenId)>0?true:false;
    }



}
