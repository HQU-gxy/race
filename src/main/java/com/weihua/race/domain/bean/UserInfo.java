package com.weihua.race.domain.bean;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;

/**
 * <p>
 * 用户身份相关信息
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2023/3/1 16:30
 */
public class UserInfo {

    public static final String ADMIN = "admin";
    public static final String COMPANY = "company";
    public static final String PROPERTY_COMPANY = "propertyCompany";
    public static final String MAINTAIN_COMPANY = "maintainCompany";
    public static final String REPAIR_COMPANY = "repairCompany";
    public static final String UNIT = "unit";
    public static final String WORKER = "worker";

    public static final String MAINTAIN_WORKER = "maintainWorker";
    public static final String REPAIR_WORKER = "repairWorker";

    /**
     * 用户名
     */
    @Getter
    private String username;

    /**
     * 角色id
     */
    @Getter
    private String roleId;

    /**
     * 账号所属公司id
     */
    @Getter
    private String companyId;

    /**
     * 账号所属班组id
     */
    @Getter
    private String groupId;

    /**
     * 账号所属单位id
     */
    @Getter
    private String unitId;

    /**
     * <p>
     * 根据JWTToken中的用户信息形成实体类
     * </p>
     *
     * @param token JWTToken
     * @date 2023-03-01 16:55:44 <br>
     */
    public UserInfo(String token) {
        DecodedJWT decode = JWT.decode(token);
        this.username = decode.getClaim("username").asString();
        this.roleId = decode.getClaim("roleId").asString();
        this.companyId = decode.getClaim("companyId").asString();
        this.unitId = decode.getClaim("unitId").asString();
        this.groupId = decode.getClaim("groupId").asString();
    }

    /**
     * <p>
     * 用户身份是否为超级管理员
     * </p>
     *
     * @return 是则返回true，否则返回false
     * @date 2023-03-01 16:54:05 <br>
     */
    public boolean isAdmin() {
        return ADMIN.equals(roleId);
    }

    /**
     * <p>
     * 用户身份是否为公司级别
     * </p>
     *
     * @return 是则返回true，否则返回false
     * @date 2023-03-01 16:54:05 <br>
     */
    public boolean isCompany() {
        return COMPANY.equals(roleId) || isPropertyCompany() || isMaintainCompany() || isRepairCompany();
    }

    public boolean isPropertyCompany() {
        return PROPERTY_COMPANY.equals(roleId);
    }

    public boolean isMaintainCompany() {
        return MAINTAIN_COMPANY.equals(roleId);
    }


    public boolean isRepairCompany() {
        return REPAIR_COMPANY.equals(roleId);
    }

    /**
     * <p>
     * 用户身份是否为单位级别
     * </p>
     *
     * @return 是则返回true，否则返回false
     * @date 2023-03-01 16:54:05 <br>
     */
    public boolean isUnit() {
        return UNIT.equals(roleId);
    }

    /**
     * <p>
     * 用户身份是否为工人
     * </p>
     *
     * @return 是则返回true，否则返回false
     * @date 2023-03-01 16:54:05 <br>
     */
    public boolean isWorker() {
        return WORKER.equals(roleId) || isMaintainWorker() || isRepairWorker();
    }

    public boolean isMaintainWorker() {
        return MAINTAIN_WORKER.equals(roleId);
    }

    public boolean isRepairWorker() {
        return REPAIR_WORKER.equals(roleId);
    }


}
