package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "com.tech.repair.po.System")
@Table(name = "system")
@Entity
@Data
public class System {
    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id")
    @Id
    private Integer id;

    /**
     * 系统名称
     */
    @ApiModelProperty(value = "系统名称")
    private String systemTitle;

    /**
     * 系统版本
     */
    @ApiModelProperty(value = "系统版本")
    private String systemVersion;

    /**
     * 系统轮播图（用；分隔）
     */
    @ApiModelProperty(value = "系统轮播图（用；分隔）")
    private String systemImages;
}

