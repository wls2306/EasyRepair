package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.RcApply")
@Data
@Entity
@Table(name="rc_apply")
public class RcApply {
    /**
     *
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
     * 维修单位编号
     */
    @ApiModelProperty(value = "维修单位编号")
    private String rcId;

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

