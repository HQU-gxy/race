package com.weihua.race.service;

import com.github.pagehelper.PageInfo;
import com.weihua.race.domain.entity.Role;
import com.weihua.race.domain.dto.RoleDTO;
import com.weihua.race.domain.vo.RoleVO;
import com.weihua.race.domain.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 针对数据库表[roleDTO]的操作Service
 * </p>
 *
 * @entity {@link RoleDTO}
 * @date 2024-02-22 14:35:20 <br>
 * @version 1.0
 */

public interface RoleService {

     /**
      * <p>
      *     根据[roleDTO]的参数分页条件查询系统角色列表
      * </p>
      * @param page    分页参数
      * @param roleDTO 查询对象条件参数
      * @return {@link PageInfo<RoleVO>} 系统角色VO分页列表
      * @date 2024-02-22 14:35:20 <br>
      */
     PageInfo<RoleVO> page(PageDTO page, RoleDTO roleDTO);

     /**
      * <p>
      *     根据[roleDTO]的参数条件查询系统角色
      * </p>
      * @param roleDTO 查询对象
      * @return {@link List<RoleVO>} 系统角色VO列表
      * @date 2024-02-22 14:35:20 <br>
      */
     List<RoleVO> list(RoleDTO roleDTO);


     /**
      * <p>
      *     添加系统角色
      * </p>
      * @param roleDTO 系统角色DTO
      * @return {@link int}
      * @date 2024-02-22 14:35:20 <br>
      */
     int add(RoleDTO roleDTO);

     /**
      * <p>
      *     更新系统角色
      * </p>
      * @param roleDTO 系统角色DTO
      * @return {@link int}
      * @date 2024-02-22 14:35:20 <br>
      */
     int update(RoleDTO roleDTO);

     /**
      * <p>
      *     通过[id]删除系统角色
      * </p>
      * @param id 系统角色id
      * @return {@link int}
      * @date 2024-02-22 14:35:20 <br>
      */
     int deleteById(String id);

}

