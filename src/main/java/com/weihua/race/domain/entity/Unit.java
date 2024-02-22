package com.weihua.race.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * [Unit]实体类
 *
 * @date 2024-02-22 14:41:56
 */
@ApiModel(description = "单位表")
@Data
public class Unit implements Serializable {

    private static final long serialVersionUID = -17109692049053519L;

    /**
     * 自增主键
     */
    @ApiModelProperty("自增主键")
    @NotNull(message = "自增主键不能为空")
    private Long id;

    /**
     * 单位id
     */
    @ApiModelProperty("单位id")
    private String unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    private String name;


}

