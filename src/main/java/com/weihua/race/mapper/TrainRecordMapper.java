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
 * @date 2023-12-12 15:19:31 <br>
 * @version 1.0
 */
@Mapper
public interface TrainRecordMapper {

    /**
     * <p>
     * 根据[trainRecordDTO]的参数条件查询pojo列表
     * </p>
     *
     * @param trainRecordDTO 查询参数
     * @return {@link List<TrainRecordDTO>} pojoDTO列表
     * @date 2023-12-12 15:19:31 <br>
     */
    List<TrainRecordVO> list(TrainRecordDTO trainRecordDTO);

    /**
     * <p>
     * 添加pojo
     * </p>
     *
     * @param trainRecord pojo实体类
     * @return {@link int}
     * @date 2023-12-12 15:19:31 <br>
     */
    int add(TrainRecord trainRecord);

    /**
     * <p>
     * 更新pojo
     * </p>
     *
     * @param trainRecord pojo
     * @return {@link int}
     * @date 2023-12-12 15:19:31 <br>
     */
    int update(TrainRecord trainRecord);

    /**
     * <p>
     * 通过[id]删除pojo
     * </p>
     *
     * @param id pojoid
     * @return {@link int}
     * @date 2023-12-12 15:19:31 <br>
     */
    int deleteById(@Param("id") String id);

}


