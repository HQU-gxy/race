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
 * [Role]VO
 *
 * @date 2024-02-22 14:35:20
 */
@ApiModel(description = "系统角色表")
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = -12422589224998941L;

    /**
     * 物理id
     */
    @ApiModelProperty("物理id")
    @NotNull(message = "物理id不能为空")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 20, message = "角色名称长度不能超过20")
    private String name;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    @NotBlank(message = "角色id不能为空")
    @Length(max = 255, message = "角色id长度不能超过255")
    private String roleId;


}

