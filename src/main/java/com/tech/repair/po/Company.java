package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.Company")
@Entity
@Table(name = "company")
@Data
public class Company {
    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 单位编号
     */
    @ApiModelProperty(value = "单位编号")
    private String companyId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String companyName;

    /**
     * 单位介绍
     */
    @ApiModelProperty(value = "单位介绍")
    private String companyDetail;

    /**
     * 单位所在地
     */
    @ApiModelProperty(value = "单位所在地")
    private String companyLocation;

    /**
     * 单位图片url
     */
    @ApiModelProperty(value = "单位图片url")
    private String companyImage;

    /**
     * 单位负责人（创建人）邮箱
     */
    @ApiModelProperty(value = "单位负责人（创建人）邮箱，唯一")
    private String companyHost;

    /**
     * 单位状态（0.不开放 1.开放）
     */
    @ApiModelProperty(value = "单位状态（0.不开放 1.开放）")
    private String companyStatus;
}

