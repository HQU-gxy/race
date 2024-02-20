package com.weihua.race.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.weihua.race.domain.entity.TrainRecord;
import com.weihua.race.domain.dto.TrainRecordDTO;
import com.weihua.race.domain.vo.TrainRecordVO;

/**
 * <p>
 * 针对数据库表[trainRecord]的操作
 * </p>
 *
 * @author hqully <br>
 * @entity {@link TrainRecord}
 * @date 2023-12-21 10:53:01 <br>
 * @version 1.0
 */
@Mapper
public interface TrainRecordMapper {

    /**
     * <p>
     * 根据[trainRecordDTO]的参数条件查询训练记录列表
     * </p>
     *
     * @param trainRecordDTO 查询参数
     * @return {@link List<TrainRecordDTO>} 训练记录DTO列表
     * @date 2023-12-21 10:53:01 <br>
     */
    List<TrainRecordVO> list(TrainRecordDTO trainRecordDTO);

    /**
     * <p>
     * 添加训练记录
     * </p>
     *
     * @param trainRecord 训练记录实体类
     * @return {@link int}
     * @date 2023-12-21 10:53:01 <br>
     */
    int add(TrainRecord trainRecord);

    /**
     * <p>
     * 更新训练记录
     * </p>
     *
     * @param trainRecord 训练记录
     * @return {@link int}
     * @date 2023-12-21 10:53:01 <br>
     */
    int update(TrainRecord trainRecord);

    /**
     * <p>
     * 通过[id]删除训练记录
     * </p>
     *
     * @param id 训练记录id
     * @return {@link int}
     * @date 2023-12-21 10:53:01 <br>
     */
    int deleteById(@Param("id") String id);

}


