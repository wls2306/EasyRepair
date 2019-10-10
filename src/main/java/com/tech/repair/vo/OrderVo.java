package com.tech.repair.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class OrderVo {

    /**
     * 申请编号
     */
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
     * 工单编号
     */
    @ApiModelProperty(value = "工单编号")
    private String orderId;

    /**
     * 工单所属单位编号
     */
    @ApiModelProperty(value = "工单所属单位编号")
    private String orderCompanyId;

    /**
     * 用户openID
     */
    @ApiModelProperty(value = "用户openID")
    private String orderUserOpenId;

    /**
     * 发起者姓名
     */
    @ApiModelProperty(value = "发起者姓名")
    private String orderUserName;

    /**
     * 发起者联系方式
     */
    @ApiModelProperty(value = "发起者联系方式")
    private String orderUserPhone;

    /**
     * 维修物品编号
     */
    @ApiModelProperty(value = "维修物品编号")
    private String orderGoodsId;

    /**
     * 工单描述（字数不超过220字）
     */
    @ApiModelProperty(value = "工单描述（字数不超过220字）")
    private String orderDescribe;

    /**
     * 工单图片地址（用;分割）
     */
    @ApiModelProperty(value = "工单图片地址（用;分割）")
    private String orderImage;

    /**
     * 指定维修员编号
     */
    @ApiModelProperty(value = "指定维修员编号")
    private String orderWorkerId;

    /**
     * 指定维修员姓名
     */
    @ApiModelProperty(value = "指定维修员姓名")
    private String orderWorkerName;

    /**
     * 指定维修员联系方式
     */
    @ApiModelProperty(value = "指定维修员联系方式")
    private String orderWorkerPhone;

    /**
     * 工单创建时间
     */
    @ApiModelProperty(value = "工单创建时间")
    private String orderCreateTime;

    /**
     * 工单状态（-1.驳回 0.未处理 1.处理中 2.已完成）
     */
    @ApiModelProperty(value = "工单状态（-1.驳回 0.未处理 1.处理中 2.已完成）")
    private String orderStatus;

    /**
     * 物品编号
     */
    @ApiModelProperty(value = "物品编号")
    private String goodsId;

    /**
     * 所属单位
     */
    @ApiModelProperty(value = "所属单位")
    private String goodsCompanyId;

    /**
     * 物品名称
     */
    @ApiModelProperty(value = "物品名称")
    private String goodsName;

    /**
     * 物品描述（220字以内）
     */
    @ApiModelProperty(value = "物品描述（220字以内）")
    private String goodsDetail;

    /**
     * 物品价值
     */
    @ApiModelProperty(value = "物品价值")
    private String goodsPrice;

    /**
     * 物品图片（单张）
     */
    @ApiModelProperty(value = "物品图片（单张）")
    private String goodsImage;


}
