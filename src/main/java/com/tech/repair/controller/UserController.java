package com.tech.repair.controller;

import com.tech.repair.po.User;
import com.tech.repair.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/openid")
    @ApiOperation(value = "根据openID获取用户")
    public User getUserByOpenId(String openId)
    {
        /**
         * @Author:Wls
         * @Date:19:54 2019/9/5
         * @Description:根据openid获取用户
         */
        logger.info("根据openid获取用户");
        return (User) userService.getUser(openId);
    }

    /**
    * @Author: Wls
    * @Date: 10:48 2019/10/6
    * @Description: 根据单位编号获取用户
    */
    @GetMapping("/company")
    @ApiOperation(value = "根据单位编号获取用户",notes = "根据userbelong中的companyId获取")
    public List<User> getUserByCompanyId(String companyId){
        logger.info("根据单位编号获取用户");
        return userService.findUserByCompanyId(companyId);
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
    @ApiOperation(value = "更新用户信息" ,notes = "根据Email或OpenId任意一项修改用户信息")
    public User updateUser(User user)throws Exception
    {
        logger.info("更新用户");
        return (User) userService.updateUser(user);
    }

    @DeleteMapping("/")
    @ApiOperation(value = "删除用户",notes = "★★ 执行此操作后，数据库中所有与 UserOpenId 相同的数据均会被删除！★★")
    public boolean deleteUserByOpenId(String userOpenId)
    {
        return userService.deleteByUserOpenId(userOpenId);
    }


    @DeleteMapping("/email")
    @ApiOperation(value = "根据邮箱删除用户")
    public boolean deleteUserByEmail(String userEmail){
        return userService.deleteUserByUserEmail(userEmail);
    }



    @PostMapping("/dologin")
    @ApiOperation(value = "通过邮箱和密码进行登录操作，返回用户对象或null")
    public User loginByEmailAndPwd(String userEmail,String password)
    {
        return userService.findUserByEmailAndPwd(userEmail, password);
    }

    @GetMapping("/email")
    @ApiOperation(value = "根据用户Email获取用户信息")
    public User getUserByUserEmail(String userEmail)
    {
        return userService.getUserByEmail(userEmail);
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
