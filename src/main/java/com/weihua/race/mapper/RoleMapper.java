package com.weihua.race.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.weihua.race.domain.entity.Role;
import com.weihua.race.domain.dto.RoleDTO;
import com.weihua.race.domain.vo.RoleVO;

/**
 * <p>
 * 针对数据库表[role]的操作
 * </p>
 *
 * @author hqully <br>
 * @entity {@link Role}
 * @date 2024-02-22 14:35:14 <br>
 * @version 1.0
 */
@Mapper
public interface RoleMapper {

    /**
     * <p>
     * 根据[roleDTO]的参数条件查询系统角色列表
     * </p>
     *
     * @param roleDTO 查询参数
     * @return {@link List<RoleDTO>} 系统角色DTO列表
     * @date 2024-02-22 14:35:14 <br>
     */
    List<RoleVO> list(RoleDTO roleDTO);

    /**
     * <p>
     * 添加系统角色
     * </p>
     *
     * @param role 系统角色实体类
     * @return {@link int}
     * @date 2024-02-22 14:35:14 <br>
     */
    int add(Role role);

    /**
     * <p>
     * 更新系统角色
     * </p>
     *
     * @param role 系统角色
     * @return {@link int}
     * @date 2024-02-22 14:35:14 <br>
     */
    int update(Role role);

    /**
     * <p>
     * 通过[id]删除系统角色
     * </p>
     *
     * @param id 系统角色id
     * @return {@link int}
     * @date 2024-02-22 14:35:14 <br>
     */
    int deleteById(@Param("id") String id);

}


