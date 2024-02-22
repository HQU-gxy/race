package com.weihua.race.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weihua.race.common.enums.ResultEnum;
import com.weihua.race.common.exception.DAOException;
import com.weihua.race.common.exception.ResultException;
import com.weihua.race.domain.entity.Role;
import com.weihua.race.domain.dto.RoleDTO;
import com.weihua.race.domain.vo.RoleVO;
import com.weihua.race.domain.dto.PageDTO;
import com.weihua.race.service.RoleService;
import com.weihua.race.mapper.RoleMapper;
import com.weihua.race.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 针对数据库表[role]的操作Service
 * </p>
 *
 * @entity {@link Role}
 * @date 2024-02-22 14:35:20 <br>
 * @version 1.0
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public PageInfo<RoleVO> page(PageDTO page, RoleDTO roleDTO) {
        try {
            PageHelper.startPage(page);
            List<RoleVO> roleList = roleMapper.list(roleDTO);
            return new PageInfo<>(roleList);
        } catch (Exception e) {
            log.error("PAGE [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", roleDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询系统角色分页列表失败");
        }
    }

    @Override
    public List<RoleVO> list(RoleDTO roleDTO) {
        try {
            return roleMapper.list(roleDTO);
       } catch (Exception e) {
            log.error("LIST [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", roleDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询系统角色列表失败");
        }
    }

    @Override
    public int add(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);

        int status;
        try {
            status = roleMapper.add(role);
        } catch (DuplicateKeyException e) {
            log.warn("ADD [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", roleDTO, e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT, "添加系统角色失败\n原因: pojoid已存在");
        } catch (Exception e) {
            log.error("ADD [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", roleDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加系统角色失败");
        }
        return status;
    }

    @Override
    public int update(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);

        int status;
        try {
            status = roleMapper.update(role);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [role] record not exist");
            }
        }catch (DAOException e){
            log.warn("UPDATE [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", roleDTO, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "更新系统角色失败\n原因: 系统角色不存在");
        }
        catch (Exception e) {
            log.error("UPDATE [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", roleDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "更新系统角色失败");
        }
        return status;
    }

    @Override
    public int deleteById(String id) {
        int status;
        try {
            status = roleMapper.deleteById(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [role] record not exist");
            }
        } catch (DAOException e) {
            log.warn("DELETE [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除系统角色失败\n原因: 系统角色不存在");
        } catch (Exception e) {
            log.error("DELETE [role] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除系统角色失败");
        }
        return status;
    }

}


