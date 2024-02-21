package com.weihua.race.domain.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * only for generate jwt token
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2023/4/18 10:24
 */
@Data
public class UserTokenInfo {

    private static final long serialVersionUID = 238395632385602448L;

    public static final String MAINTAIN_WORKER = "maintainWorker";
    public static final String REPAIR_WORKER = "repairWorker";

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
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    private String roleId;

    /**
     * 账号所属公司id
     */
    @ApiModelProperty("账号所属公司id")
    private String companyId;

    /**
     * 账号所属班组id
     */
    @ApiModelProperty("账号所属班组id")
    private String groupId;


    public boolean isWorker() {
        return isMaintainWorker() || isRepairWorker();
    }

    public boolean isMaintainWorker() {
        return MAINTAIN_WORKER.equals(roleId);
    }

    public boolean isRepairWorker() {
        return REPAIR_WORKER.equals(roleId);
    }

}
