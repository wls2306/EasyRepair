package com.tech.repair.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterInfoVo {


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
     * 单位状态（0.不开放 1.开放）
     */
    @ApiModelProperty(value = "单位状态（0.不开放 1.开放）")
    private String companyStatus;

    /**
     * 回答内容（用;分隔）
     */
    @ApiModelProperty(value = "回答内容（用;分隔）")
    private String registerAnswer;


    /**
     * 申请状态（-1.拒绝 0.待审核 1.通过）
     */
    @ApiModelProperty(value = "申请状态（-1.拒绝 0.待审核 1.通过）")
    private String registerStatus;



}
