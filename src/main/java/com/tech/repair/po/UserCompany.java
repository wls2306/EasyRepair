package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.UserCompany")
@Entity
@Table(name = "user_company")
@Data
public class UserCompany {
    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户openId
     */
    @ApiModelProperty(value = "用户openId")
    private String userOpenId;

    /**
     * 单位编号
     */
    @ApiModelProperty(value = "单位编号")
    private String companyId;
}

