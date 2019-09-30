package com.tech.repair.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RcVo {
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

    /**
     * 维修单位编号
     */
    @ApiModelProperty(value = "维修单位编号")
    private String rcId;

    /**
     * 维修单位名称
     */
    @ApiModelProperty(value = "维修单位名称")
    private String rcName;

    /**
     * 维修单位负责人邮箱
     */
    @ApiModelProperty(value = "维修单位负责人邮箱")
    private String rcHost;


    /**
     * 申请理由（255字之内）
     */
    @ApiModelProperty(value = "申请理由（255字之内）")
    private String reason;

    /**
     * 申请状态 (-1拒绝,0未处理,1通过)
     */
    @ApiModelProperty(value = "申请状态 (-1拒绝,0未处理,1通过)")
    private String status;

}
