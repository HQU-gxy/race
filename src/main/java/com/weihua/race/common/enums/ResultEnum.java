package com.weihua.race.common.enums;

import lombok.Getter;

/**
 * <p>
 * 后台返回结果集枚举
 * </p>
 *
 * @author liulingyu
 * @version 1.0
 * @date 2022-05-07 15:43
 */
@Getter
public enum ResultEnum {

    /**
     * 通用状态
     */
    SUCCESS(20000, "成功"),
    ERROR(40000, "错误"),

    /**
     * 账户问题
     */
    USER_EXIST(40001, "该用户名已经存在"),
    USER_PWD_NULL(40002, "密码不能为空"),
    USER_INEQUALITY(40003, "两次密码不一致"),
    USER_OLD_PWD_ERROR(40004, "原来密码不正确"),
    USER_NAME_PWD_NULL(40005, "用户名和密码不能为空"),
    USER_CAPTCHA_ERROR(40006, "验证码错误"),
    USER_LOGOUT(40007, "用户已退出登录"),
    USER_PWD_SAME(40008, "新密码不能与旧密码相同"),
    USER_LOCKED(40009, "该账号已停用"),
    USER_NAME_PWD_ERROR(40010, "用户名或密码错误"),
    NO_PERMISSIONS(40001, "权限不足！"),

    FILE_ILLEGAL_TYPE(30001, "文件类型错误"),
    FILE_UPLOAD_FAIL(30002, "文件上传失败"),
    FILE_EXCEEDED_MAX_SIZE(30003, "文件大小超出限制"),

    /**
     * 数据库问题
     */
    PRIMARY_KEY_CONFLICT(50001, "主键冲突"),
    DUPLICATE_KEY_CONFLICT(50002, "键冲突"),
    BIND_CONFLICT(50003, "绑定冲突"),
    RESULT_NULL(50004, "返回数据为空");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
