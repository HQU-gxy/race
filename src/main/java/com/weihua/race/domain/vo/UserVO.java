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
 * [User]VO
 *
 * @date 2024-02-22 14:43:40
 */
@ApiModel(description = "用户表")
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 353563087627725597L;

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
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
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


    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    private String unitName;


    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;

}

