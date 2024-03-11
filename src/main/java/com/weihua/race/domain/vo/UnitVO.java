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
 * [Unit]VO
 *
 * @date 2024-02-22 14:41:56
 */
@ApiModel(description = "单位表")
@Data
public class UnitVO implements Serializable {

    private static final long serialVersionUID = -70948730355004705L;

    /**
     * 自增主键
     */
    @ApiModelProperty("自增主键")
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

