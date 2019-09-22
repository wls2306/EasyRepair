package com.tech.repair.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@ApiModel(value = "com.tech.repair.po.Notice")
@Entity
@Table(name = "notice")
@Data
public class Notice {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "自增id")
    private Integer id;

    /**
     * 公告标题 （50字）
     */
    @ApiModelProperty(value = "公告标题 （50字）")
    private String noticeTitle;

    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容")
    private String noticeContent;

    /**
     * 公告类型（1.针对某个单位的内部公告 2.系统全体公告）
     */
    @ApiModelProperty(value = "公告类型（1.针对某个单位的内部公告 2.系统全体公告）")
    private String noticeType;

    /**
     * 公告所属单位id
     */
    @ApiModelProperty(value = "公告所属单位id")
    private String noticeCompanyId;

    /**
     * 公告状态
     */
    @ApiModelProperty(value = "公告状态")
    private String noticeStatus;
}

