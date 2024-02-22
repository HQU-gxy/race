package com.weihua.race.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.weihua.race.domain.entity.Feedback;
import com.weihua.race.domain.dto.FeedbackDTO;
import com.weihua.race.domain.vo.FeedbackVO;

/**
 * <p>
 * 针对数据库表[feedback]的操作
 * </p>
 *
 * @author hqully <br>
 * @entity {@link Feedback}
 * @date 2024-02-22 16:51:35 <br>
 * @version 1.0
 */
@Mapper
public interface FeedbackMapper {

    /**
     * <p>
     * 根据[feedbackDTO]的参数条件查询用户反馈列表
     * </p>
     *
     * @param feedbackDTO 查询参数
     * @return {@link List<FeedbackDTO>} 用户反馈DTO列表
     * @date 2024-02-22 16:51:35 <br>
     */
    List<FeedbackVO> list(FeedbackDTO feedbackDTO);

    /**
     * <p>
     * 添加用户反馈
     * </p>
     *
     * @param feedback 用户反馈实体类
     * @return {@link int}
     * @date 2024-02-22 16:51:35 <br>
     */
    int add(Feedback feedback);

    /**
     * <p>
     * 更新用户反馈
     * </p>
     *
     * @param feedback 用户反馈
     * @return {@link int}
     * @date 2024-02-22 16:51:35 <br>
     */
    int update(Feedback feedback);

    /**
     * <p>
     * 通过[id]删除用户反馈
     * </p>
     *
     * @param id 用户反馈id
     * @return {@link int}
     * @date 2024-02-22 16:51:35 <br>
     */
    int deleteById(@Param("id") String id);

}


