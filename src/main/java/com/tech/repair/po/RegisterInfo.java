package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.RegisterInfo")
@Entity
@Table(name = "register_info")
@Data
public class RegisterInfo {
    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 预申请单位编号
     */
    @ApiModelProperty(value = "预申请单位编号")
    private String companyId;

    /**
     * 申请者OpenId
     */
    @ApiModelProperty(value = "申请者OpenId")
    private String openId;

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

