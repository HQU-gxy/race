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
 * [User]DTO
 *
 * @date 2024-02-22 14:43:38
 */
@ApiModel(description = "用户表")
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -31913675495141019L;

    /**
     * 自增主键
     */
    @ApiModelProperty("自增主键")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String userId;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private String roleId;

    /**
     * 所属单位id
     */
    @ApiModelProperty("所属单位id")
    private String unitId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 加密盐值
     */
    @ApiModelProperty("加密盐值")
    private String salt;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 体重(kg)
     */
    @ApiModelProperty("体重(kg)")
    private Double weight;

    /**
     * 身高(m)
     */
    @ApiModelProperty("身高(m)")
    private Double height;

    /**
     * 是否是运动员
     */
    @ApiModelProperty("是否是运动员")
    private Boolean athlete;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer state;


}

