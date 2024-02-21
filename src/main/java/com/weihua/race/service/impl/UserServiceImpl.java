package com.weihua.race.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weihua.race.common.enums.ResultEnum;
import com.weihua.race.common.exception.DAOException;
import com.weihua.race.common.exception.ResultException;
import com.weihua.race.domain.entity.User;
import com.weihua.race.domain.dto.UserDTO;
import com.weihua.race.domain.vo.UserVO;
import com.weihua.race.domain.dto.PageDTO;
import com.weihua.race.service.UserService;
import com.weihua.race.mapper.UserMapper;
import com.weihua.race.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 针对数据库表[user]的操作Service
 * </p>
 *
 * @entity {@link User}
 * @date 2023-12-12 15:17:33 <br>
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User getUserByUsername(String username) {
        try {
            return userMapper.getByUsername(username);
        } catch (Exception e) {
            log.error("QUERY [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", username, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询用户失败");
        }
    }
    @Override
    public PageInfo<UserVO> page(PageDTO page, UserDTO userDTO) {
        try {
            PageHelper.startPage(page);
            List<UserVO> userList = userMapper.list(userDTO);
            return new PageInfo<>(userList);
        } catch (Exception e) {
            log.error("PAGE [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", userDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询用户分页列表失败");
        }
    }

    @Override
    public List<UserVO> list(UserDTO userDTO) {
        try {
            return userMapper.list(userDTO);
       } catch (Exception e) {
            log.error("LIST [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", userDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询用户列表失败");
        }
    }

    @Override
    public int add(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        int status;
        try {
            status = userMapper.add(user);
        } catch (DuplicateKeyException e) {
            log.warn("ADD [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", userDTO, e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT, "添加用户失败\n原因: pojoid已存在");
        } catch (Exception e) {
            log.error("ADD [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", userDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加用户失败");
        }
        return status;
    }

    @Override
    public int update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        int status;
        try {
            status = userMapper.update(user);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [user] record not exist");
            }
        }catch (DAOException e){
            log.warn("UPDATE [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", userDTO, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "更新用户失败\n原因: 用户不存在");
        }
        catch (Exception e) {
            log.error("UPDATE [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", userDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "更新用户失败");
        }
        return status;
    }

    @Override
    public int deleteById(String id) {
        int status;
        try {
            status = userMapper.deleteById(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [user] record not exist");
            }
        } catch (DAOException e) {
            log.warn("DELETE [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除用户失败\n原因: 用户不存在");
        } catch (Exception e) {
            log.error("DELETE [user] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除用户失败");
        }
        return status;
    }

}


