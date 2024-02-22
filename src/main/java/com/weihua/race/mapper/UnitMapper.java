package com.weihua.race.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.weihua.race.domain.entity.Unit;
import com.weihua.race.domain.dto.UnitDTO;
import com.weihua.race.domain.vo.UnitVO;

/**
 * <p>
 * 针对数据库表[unit]的操作
 * </p>
 *
 * @author hqully <br>
 * @entity {@link Unit}
 * @date 2024-02-22 14:41:56 <br>
 * @version 1.0
 */
@Mapper
public interface UnitMapper {

    /**
     * <p>
     * 根据[unitDTO]的参数条件查询单位列表
     * </p>
     *
     * @param unitDTO 查询参数
     * @return {@link List<UnitDTO>} 单位DTO列表
     * @date 2024-02-22 14:41:56 <br>
     */
    List<UnitVO> list(UnitDTO unitDTO);

    /**
     * <p>
     * 添加单位
     * </p>
     *
     * @param unit 单位实体类
     * @return {@link int}
     * @date 2024-02-22 14:41:56 <br>
     */
    int add(Unit unit);

    /**
     * <p>
     * 更新单位
     * </p>
     *
     * @param unit 单位
     * @return {@link int}
     * @date 2024-02-22 14:41:56 <br>
     */
    int update(Unit unit);

    /**
     * <p>
     * 通过[id]删除单位
     * </p>
     *
     * @param id 单位id
     * @return {@link int}
     * @date 2024-02-22 14:41:56 <br>
     */
    int deleteById(@Param("id") String id);

}


