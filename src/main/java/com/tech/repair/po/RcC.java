package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.RcC")
@Entity
@Table(name = "rc_c")
@Data
public class RcC {
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
     * 单位编号
     */
    @ApiModelProperty(value = "单位编号")
    private String companyId;
}

