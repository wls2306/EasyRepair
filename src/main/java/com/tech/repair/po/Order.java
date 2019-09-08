package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApiModel(value = "com.tech.repair.po.Order")
@Data
public class Order {
    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

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
    private String oederUserName;

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
    private Date orderCreateTime;

    /**
     * 工单状态（-1.驳回 0.未处理 1.处理中 2.已完成）
     */
    @ApiModelProperty(value = "工单状态（-1.驳回 0.未处理 1.处理中 2.已完成）")
    private String orderStatus;
}

