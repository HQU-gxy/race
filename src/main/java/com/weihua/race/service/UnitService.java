package com.weihua.race.service;

import com.github.pagehelper.PageInfo;
import com.weihua.race.domain.entity.Unit;
import com.weihua.race.domain.dto.UnitDTO;
import com.weihua.race.domain.vo.UnitVO;
import com.weihua.race.domain.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 针对数据库表[unitDTO]的操作Service
 * </p>
 *
 * @entity {@link UnitDTO}
 * @date 2024-02-22 14:41:56 <br>
 * @version 1.0
 */

public interface UnitService {

     /**
      * <p>
      *     根据[unitDTO]的参数分页条件查询单位列表
      * </p>
      * @param page    分页参数
      * @param unitDTO 查询对象条件参数
      * @return {@link PageInfo<UnitVO>} 单位VO分页列表
      * @date 2024-02-22 14:41:56 <br>
      */
     PageInfo<UnitVO> page(PageDTO page, UnitDTO unitDTO);

     /**
      * <p>
      *     根据[unitDTO]的参数条件查询单位
      * </p>
      * @param unitDTO 查询对象
      * @return {@link List<UnitVO>} 单位VO列表
      * @date 2024-02-22 14:41:56 <br>
      */
     List<UnitVO> list(UnitDTO unitDTO);


     /**
      * <p>
      *     添加单位
      * </p>
      * @param unitDTO 单位DTO
      * @return {@link int}
      * @date 2024-02-22 14:41:56 <br>
      */
     int add(UnitDTO unitDTO);

     /**
      * <p>
      *     更新单位
      * </p>
      * @param unitDTO 单位DTO
      * @return {@link int}
      * @date 2024-02-22 14:41:56 <br>
      */
     int update(UnitDTO unitDTO);

     /**
      * <p>
      *     通过[id]删除单位
      * </p>
      * @param id 单位id
      * @return {@link int}
      * @date 2024-02-22 14:41:56 <br>
      */
     int deleteById(String id);

}

