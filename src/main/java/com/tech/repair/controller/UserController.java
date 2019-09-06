package com.tech.repair.controller;

import com.tech.repair.pojo.User;
import com.tech.repair.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @GetMapping("/all")
    @ApiOperation(value = "获取所有用户")
    public List<User> getAllUser()
    {
      /**
       * @Author:Wls
       * @Description: 获取所有用户
       * @Date:18:59 2019/9/5
       */
      logger.info("获取所有用户");
      return (List<User>)userService.getUser("all");
    }


    @GetMapping("/{openId}")
    @ApiOperation(value = "根据openID获取用户")
    public User getUserByOpenId(@PathVariable("openId")String openId)
    {
        /**
         * @Author:Wls
         * @Date:19:54 2019/9/5
         * @Description:根据openid获取用户
         */
        logger.info("根据openid获取用户");
        return (User) userService.getUser(openId);
    }

    @PostMapping("/login")
    @ApiOperation(value = "微信登录",notes = "成功则返回openid，失败请查看errmsg")
    public String WXlogin(String js_code)
    {
        /**
         * @Author:Wls
         * @Date:19:59 2019/9/5
         * @Description: 微信登录
         */
        logger.info("微信登录");
        return userService.wxLogin(js_code);
    }

    @PostMapping("/")
    @ApiOperation(value = "添加用户信息")
    public Object addUser(User user)throws Exception
    {
        /**
         * @Author:Wls
         * @Date:20:12 2019/9/5
         * @Description: 注册新用户
         */
        logger.info("注册用户");
        return userService.addUser(user);
    }

    @PutMapping("/")
    @ApiOperation(value = "更新用户信息")
    public User updateUser(User u)throws Exception
    {
        /**
         * @Author:Wls
         * @Date:13:10 2019/9/6
         * @Description: 更新用户信息
         */
        logger.info("更新用户");
        return (User) updateUser(u);
    }



  /*  @PutMapping("/{openId}")
    @ApiOperation(value = "更新用户当前选择单位",notes = "{\"result\":\"true/false\"}")
    public HashMap updateUserCompany(String userCompanyId, String userOpenId)
    {
        HashMap map=new HashMap();
        logger.info("更新用户当前选择单位");
        map.put("result",userService.updateUserCompanyByUserOpenId(userCompanyId,userOpenId));
        return map;
    }*/


}
