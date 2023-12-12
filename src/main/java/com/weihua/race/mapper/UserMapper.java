package com.weihua.race.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.weihua.race.domain.entity.User;
import com.weihua.race.domain.dto.UserDTO;
import com.weihua.race.domain.vo.UserVO;

/**
 * <p>
 * 针对数据库表[user]的操作
 * </p>
 *
 * @author hqully <br>
 * @entity {@link User}
 * @date 2023-12-12 15:09:20 <br>
 * @version 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * <p>
     * 根据[userDTO]的参数条件查询用户列表
     * </p>
     *
     * @param userDTO 查询参数
     * @return {@link List<UserDTO>} 用户DTO列表
     * @date 2023-12-12 15:09:20 <br>
     */
    List<UserVO> list(UserDTO userDTO);

    /**
     * <p>
     * 添加用户
     * </p>
     *
     * @param user 用户实体类
     * @return {@link int}
     * @date 2023-12-12 15:09:20 <br>
     */
    int add(User user);

    /**
     * <p>
     * 更新用户
     * </p>
     *
     * @param user 用户
     * @return {@link int}
     * @date 2023-12-12 15:09:20 <br>
     */
    int update(User user);

    /**
     * <p>
     * 通过[id]删除用户
     * </p>
     *
     * @param id 用户id
     * @return {@link int}
     * @date 2023-12-12 15:09:20 <br>
     */
    int deleteById(@Param("id") String id);

}


