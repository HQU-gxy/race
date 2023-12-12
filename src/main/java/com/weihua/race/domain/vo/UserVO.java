package com.weihua.race.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * [User]VO
 *
 * @author hqully
 * @date 2023-12-12 15:27:28
 */
@ApiModel(description = "用户表")
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = -69568839407288839L;

    /**
     * 自增主键
     */
    @ApiModelProperty("自增主键")
    private Integer id;

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


}

