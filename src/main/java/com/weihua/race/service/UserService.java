package com.weihua.race.service;

import com.github.pagehelper.PageInfo;
import com.weihua.race.domain.entity.User;
import com.weihua.race.domain.dto.UserDTO;
import com.weihua.race.domain.vo.UserVO;
import com.weihua.race.domain.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 针对数据库表[userDTO]的操作Service
 * </p>
 *
 * @entity {@link UserDTO}
 * @date 2023-12-12 15:09:21 <br>
 * @version 1.0
 */

public interface UserService {

     User getUserByUsername(String username);

     /**
      * <p>
      *     根据[userDTO]的参数分页条件查询用户列表
      * </p>
      * @param page    分页参数
      * @param userDTO 查询对象条件参数
      * @return {@link PageInfo<UserVO>} 用户VO分页列表
      * @date 2023-12-12 15:09:21 <br>
      */
     PageInfo<UserVO> page(PageDTO page, UserDTO userDTO);

     /**
      * <p>
      *     根据[userDTO]的参数条件查询用户
      * </p>
      * @param userDTO 查询对象
      * @return {@link List<UserVO>} 用户VO列表
      * @date 2023-12-12 15:09:21 <br>
      */
     List<UserVO> list(UserDTO userDTO);


     /**
      * <p>
      *     添加用户
      * </p>
      * @param userDTO 用户DTO
      * @return {@link int}
      * @date 2023-12-12 15:09:21 <br>
      */
     int add(UserDTO userDTO);

     /**
      * <p>
      *     更新用户
      * </p>
      * @param userDTO 用户DTO
      * @return {@link int}
      * @date 2023-12-12 15:09:21 <br>
      */
     int update(UserDTO userDTO);

     /**
      * <p>
      *     通过[id]删除用户
      * </p>
      * @param id 用户id
      * @return {@link int}
      * @date 2023-12-12 15:09:21 <br>
      */
     int deleteById(String id);

}

