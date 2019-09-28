package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.RepairCompany")
@Entity
@Table(name = "repair_table")
@Data
public class RepairCompany {
    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 维修单位图片
     */
    @ApiModelProperty(value = "维修单位图片")
    private String rcImage;
}

