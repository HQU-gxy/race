package com.weihua.race.service;

import com.github.pagehelper.PageInfo;
import com.weihua.race.domain.entity.Feedback;
import com.weihua.race.domain.dto.FeedbackDTO;
import com.weihua.race.domain.vo.FeedbackVO;
import com.weihua.race.domain.dto.PageDTO;

import java.util.List;

/**
 * <p>
 * 针对数据库表[feedbackDTO]的操作Service
 * </p>
 *
 * @entity {@link FeedbackDTO}
 * @date 2024-02-22 16:51:36 <br>
 * @version 1.0
 */

public interface FeedbackService {

     /**
      * <p>
      *     根据[feedbackDTO]的参数分页条件查询用户反馈列表
      * </p>
      * @param page    分页参数
      * @param feedbackDTO 查询对象条件参数
      * @return {@link PageInfo<FeedbackVO>} 用户反馈VO分页列表
      * @date 2024-02-22 16:51:36 <br>
      */
     PageInfo<FeedbackVO> page(PageDTO page, FeedbackDTO feedbackDTO);

     /**
      * <p>
      *     根据[feedbackDTO]的参数条件查询用户反馈
      * </p>
      * @param feedbackDTO 查询对象
      * @return {@link List<FeedbackVO>} 用户反馈VO列表
      * @date 2024-02-22 16:51:36 <br>
      */
     List<FeedbackVO> list(FeedbackDTO feedbackDTO);


     /**
      * <p>
      *     添加用户反馈
      * </p>
      * @param feedbackDTO 用户反馈DTO
      * @return {@link int}
      * @date 2024-02-22 16:51:36 <br>
      */
     int add(FeedbackDTO feedbackDTO);

     /**
      * <p>
      *     更新用户反馈
      * </p>
      * @param feedbackDTO 用户反馈DTO
      * @return {@link int}
      * @date 2024-02-22 16:51:36 <br>
      */
     int update(FeedbackDTO feedbackDTO);

     /**
      * <p>
      *     通过[id]删除用户反馈
      * </p>
      * @param id 用户反馈id
      * @return {@link int}
      * @date 2024-02-22 16:51:36 <br>
      */
     int deleteById(String id);

}

