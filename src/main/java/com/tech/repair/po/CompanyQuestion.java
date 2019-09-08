package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.CompanyQuestion")
@Table(name = "company_question")
@Entity
@Data
public class CompanyQuestion {
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
     * 问题数量
     */
    @ApiModelProperty(value = "问题数量")
    private String questionCount;

    /**
     * 问题内容（用;分隔）
     */
    @ApiModelProperty(value = "问题内容（用;分隔）")
    private String questionContent;
}

