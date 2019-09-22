package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.Progress")
@Entity
@Table(name = "progress")
@Data
public class Progress {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "自增id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String progressOrderId;

    /**
     * 进度标题（50字以内）
     */
    @ApiModelProperty(value = "进度标题（50字以内）")
    private String progressTitle;

    /**
     * 进度内容（220字以内）
     */
    @ApiModelProperty(value = "进度内容（220字以内）")
    private String progressContent;

    /**
     * 提交时间
     */
    @ApiModelProperty(value = "提交时间")
    private Date progressTime;
}

