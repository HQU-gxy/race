package com.weihua.race.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * [TrainRecord]DTO
 *
 * @date 2023-12-21 10:53:03
 */
@ApiModel(description = "训练记录表")
@Data
public class TrainRecordDTO implements Serializable {

    private static final long serialVersionUID = 349168662309756412L;

    /**
     * 自增主键
     */
    @ApiModelProperty("自增主键")
    private Integer id;

    /**
     * 训练记录id
     */
    @ApiModelProperty("训练记录id")
    private String trainId;

    /**
     * 训练者姓名
     */
    @ApiModelProperty("训练者姓名")
    private String name;

    /**
     * 训练者性别
     */
    @ApiModelProperty("训练者性别")
    private String gender;

    /**
     * 训练者年龄
     */
    @ApiModelProperty("训练者年龄")
    private Integer age;

    /**
     * 训练者身高
     */
    @ApiModelProperty("训练者身高")
    private Double height;

    /**
     * 训练者体重
     */
    @ApiModelProperty("训练者体重")
    private Double weight;

    /**
     * 训练时间
     */
    @ApiModelProperty("训练时间")
    private Date time;

    /**
     * 预测最大摄氧量
     */
    @ApiModelProperty("预测最大摄氧量")
    private Double vo2max;

    /**
     * 训练者id
     */
    @ApiModelProperty("训练者id")
    private String trainerId;


}

