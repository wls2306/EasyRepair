package com.tech.repair.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "com.tech.repair.pojo.User")
@Data
@Entity
@Table(name = "user")
public class User {
    /**
     * 自增id
     */
    @Id
    @ApiModelProperty(value = "自增id")
    private Integer id;

    /**
     * 用户OpenId
     */
    @ApiModelProperty(value = "用户OpenId")
    private String userOpenId;

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

