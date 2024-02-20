package com.weihua.race.service;

import com.github.pagehelper.PageInfo;
import com.weihua.race.domain.entity.TrainRecord;
import com.weihua.race.domain.dto.TrainRecordDTO;
import com.weihua.race.domain.vo.TrainRecordVO;
import com.weihua.race.domain.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 针对数据库表[trainRecordDTO]的操作Service
 * </p>
 *
 * @entity {@link TrainRecordDTO}
 * @date 2023-12-21 10:53:02 <br>
 * @version 1.0
 */

public interface TrainRecordService {

     /**
      * <p>
      *     根据[trainRecordDTO]的参数分页条件查询训练记录列表
      * </p>
      * @param page    分页参数
      * @param trainRecordDTO 查询对象条件参数
      * @return {@link PageInfo<TrainRecordVO>} 训练记录VO分页列表
      * @date 2023-12-21 10:53:02 <br>
      */
     PageInfo<TrainRecordVO> page(PageDTO page, TrainRecordDTO trainRecordDTO);

     /**
      * <p>
      *     根据[trainRecordDTO]的参数条件查询训练记录
      * </p>
      * @param trainRecordDTO 查询对象
      * @return {@link List<TrainRecordVO>} 训练记录VO列表
      * @date 2023-12-21 10:53:02 <br>
      */
     List<TrainRecordVO> list(TrainRecordDTO trainRecordDTO);


     /**
      * <p>
      *     添加训练记录
      * </p>
      * @param trainRecordDTO 训练记录DTO
      * @return {@link int}
      * @date 2023-12-21 10:53:02 <br>
      */
     int add(TrainRecordDTO trainRecordDTO);

     /**
      * <p>
      *     更新训练记录
      * </p>
      * @param trainRecordDTO 训练记录DTO
      * @return {@link int}
      * @date 2023-12-21 10:53:02 <br>
      */
     int update(TrainRecordDTO trainRecordDTO);

     /**
      * <p>
      *     通过[id]删除训练记录
      * </p>
      * @param id 训练记录id
      * @return {@link int}
      * @date 2023-12-21 10:53:02 <br>
      */
     int deleteById(String id);

}

