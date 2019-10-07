package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.Goods")
@Entity
@Table(name = "goods")
@Data
public class Goods {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "自增id")
    private Integer id;

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

