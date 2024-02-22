package com.weihua.race.domain.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * [Feedback]VO
 *
 * @date 2024-02-22 16:51:37
 */
@ApiModel(description = "用户反馈表")
@Data
public class FeedbackVO implements Serializable {

    private static final long serialVersionUID = 243838433558308112L;

    /**
     * 自增主键
     */
    @ApiModelProperty("自增主键")
    @NotNull(message = "自增主键不能为空")
    private Long id;

    /**
     * 反馈发送者id
     */
    @ApiModelProperty("反馈发送者id")
    private String userId;

    /**
     * 反馈者姓名
     */
    @ApiModelProperty("反馈者姓名")
    private String name;

    /**
     * 反馈者性别
     */
    @ApiModelProperty("反馈者性别")
    private String gender;

    /**
     * 反馈者年龄
     */
    @ApiModelProperty("反馈者年龄")
    private Integer age;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;

    /**
     * 联系单位
     */
    @ApiModelProperty("联系单位")
    private String unit;

    /**
     * 反馈内容
     */
    @ApiModelProperty("反馈内容")
    private String content;

    /**
     * 反馈类型,1为反馈意见,2为报错提交.
     */
    @ApiModelProperty("反馈类型,1为反馈意见,2为报错提交.")
    private Integer type;


}

