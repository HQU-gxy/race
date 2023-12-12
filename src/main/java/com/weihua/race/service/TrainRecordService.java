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
 * @date 2023-12-12 15:19:38 <br>
 * @version 1.0
 */

public interface TrainRecordService {

     /**
      * <p>
      *     根据[trainRecordDTO]的参数分页条件查询pojo列表
      * </p>
      * @param page    分页参数
      * @param trainRecordDTO 查询对象条件参数
      * @return {@link PageInfo<TrainRecordVO>} pojoVO分页列表
      * @date 2023-12-12 15:19:38 <br>
      */
     PageInfo<TrainRecordVO> page(PageDTO page, TrainRecordDTO trainRecordDTO);

     /**
      * <p>
      *     根据[trainRecordDTO]的参数条件查询pojo
      * </p>
      * @param trainRecordDTO 查询对象
      * @return {@link List<TrainRecordVO>} pojoVO列表
      * @date 2023-12-12 15:19:38 <br>
      */
     List<TrainRecordVO> list(TrainRecordDTO trainRecordDTO);


     /**
      * <p>
      *     添加pojo
      * </p>
      * @param trainRecordDTO pojoDTO
      * @return {@link int}
      * @date 2023-12-12 15:19:38 <br>
      */
     int add(TrainRecordDTO trainRecordDTO);

     /**
      * <p>
      *     更新pojo
      * </p>
      * @param trainRecordDTO pojoDTO
      * @return {@link int}
      * @date 2023-12-12 15:19:38 <br>
      */
     int update(TrainRecordDTO trainRecordDTO);

     /**
      * <p>
      *     通过[id]删除pojo
      * </p>
      * @param id pojoid
      * @return {@link int}
      * @date 2023-12-12 15:19:38 <br>
      */
     int deleteById(String id);

}

