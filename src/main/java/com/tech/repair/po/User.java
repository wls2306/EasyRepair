package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.User")
@Data
@Entity
@Table(name = "user")
public class User {
    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户OpenId 要求user_open_idl唯一
     */
    @ApiModelProperty(value = "用户OpenId 要求user_open_idl唯一")
    private String userOpenId;

    /**
     * 用户Email 要求user_email唯一
     */
    @ApiModelProperty(value = "用户Email 要求user_email唯一")
    private String userEmail;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String userPwd;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 用户联系方式
     */
    @ApiModelProperty(value = "用户联系方式")
    private String userPhone;

    /**
     * 用户当前选择单位
     */
    @ApiModelProperty(value = "用户当前选择单位")
    private String userCompanyId;

    /**
     * 所属单位id
     */
    @ApiModelProperty(value = "所属单位id")
    private String userBelong;

    /**
     * 用户角色（1.用户 2.维修员 3.管理员）
     */
    @ApiModelProperty(value = "用户角色（1.用户 2.维修员 3.管理员）")
    private String userRole;

    /**
     * 用户状态（1.正常 2.封禁）
     */
    @ApiModelProperty(value = "用户状态（1.正常 2.封禁）")
    private String userStatus;
}

