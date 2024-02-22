package com.weihua.race.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * [Unit]DTO
 *
 * @date 2024-02-22 14:41:56
 */
@ApiModel(description = "单位表")
@Data
public class UnitDTO implements Serializable {

    private static final long serialVersionUID = 802786883518125716L;

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

