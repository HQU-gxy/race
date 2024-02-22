package com.weihua.race.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weihua.race.common.enums.ResultEnum;
import com.weihua.race.common.exception.DAOException;
import com.weihua.race.common.exception.ResultException;
import com.weihua.race.domain.entity.Unit;
import com.weihua.race.domain.dto.UnitDTO;
import com.weihua.race.domain.vo.UnitVO;
import com.weihua.race.domain.dto.PageDTO;
import com.weihua.race.service.UnitService;
import com.weihua.race.mapper.UnitMapper;
import com.weihua.race.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 针对数据库表[unit]的操作Service
 * </p>
 *
 * @entity {@link Unit}
 * @date 2024-02-22 14:41:56 <br>
 * @version 1.0
 */
@Slf4j
@Service
public class UnitServiceImpl implements UnitService {

    @Resource
    private UnitMapper unitMapper;

    @Override
    public PageInfo<UnitVO> page(PageDTO page, UnitDTO unitDTO) {
        try {
            PageHelper.startPage(page);
            List<UnitVO> unitList = unitMapper.list(unitDTO);
            return new PageInfo<>(unitList);
        } catch (Exception e) {
            log.error("PAGE [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", unitDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询单位分页列表失败");
        }
    }

    @Override
    public List<UnitVO> list(UnitDTO unitDTO) {
        try {
            return unitMapper.list(unitDTO);
       } catch (Exception e) {
            log.error("LIST [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", unitDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询单位列表失败");
        }
    }

    @Override
    public int add(UnitDTO unitDTO) {
        Unit unit = new Unit();
        BeanUtils.copyProperties(unitDTO, unit);

        int status;
        try {
            status = unitMapper.add(unit);
        } catch (DuplicateKeyException e) {
            log.warn("ADD [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", unitDTO, e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT, "添加单位失败\n原因: pojoid已存在");
        } catch (Exception e) {
            log.error("ADD [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", unitDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加单位失败");
        }
        return status;
    }

    @Override
    public int update(UnitDTO unitDTO) {
        Unit unit = new Unit();
        BeanUtils.copyProperties(unitDTO, unit);

        int status;
        try {
            status = unitMapper.update(unit);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [unit] record not exist");
            }
        }catch (DAOException e){
            log.warn("UPDATE [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", unitDTO, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "更新单位失败\n原因: 单位不存在");
        }
        catch (Exception e) {
            log.error("UPDATE [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", unitDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "更新单位失败");
        }
        return status;
    }

    @Override
    public int deleteById(String id) {
        int status;
        try {
            status = unitMapper.deleteById(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [unit] record not exist");
            }
        } catch (DAOException e) {
            log.warn("DELETE [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除单位失败\n原因: 单位不存在");
        } catch (Exception e) {
            log.error("DELETE [unit] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除单位失败");
        }
        return status;
    }

}


