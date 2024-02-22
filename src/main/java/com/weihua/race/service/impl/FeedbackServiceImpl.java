package com.weihua.race.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weihua.race.common.enums.ResultEnum;
import com.weihua.race.common.exception.DAOException;
import com.weihua.race.common.exception.ResultException;
import com.weihua.race.domain.entity.Feedback;
import com.weihua.race.domain.dto.FeedbackDTO;
import com.weihua.race.domain.vo.FeedbackVO;
import com.weihua.race.domain.dto.PageDTO;
import com.weihua.race.service.FeedbackService;
import com.weihua.race.mapper.FeedbackMapper;
import com.weihua.race.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 针对数据库表[feedback]的操作Service
 * </p>
 *
 * @entity {@link Feedback}
 * @date 2024-02-22 16:51:36 <br>
 * @version 1.0
 */
@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public PageInfo<FeedbackVO> page(PageDTO page, FeedbackDTO feedbackDTO) {
        try {
            PageHelper.startPage(page);
            List<FeedbackVO> feedbackList = feedbackMapper.list(feedbackDTO);
            return new PageInfo<>(feedbackList);
        } catch (Exception e) {
            log.error("PAGE [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", feedbackDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询用户反馈分页列表失败");
        }
    }

    @Override
    public List<FeedbackVO> list(FeedbackDTO feedbackDTO) {
        try {
            return feedbackMapper.list(feedbackDTO);
       } catch (Exception e) {
            log.error("LIST [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", feedbackDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询用户反馈列表失败");
        }
    }

    @Override
    public int add(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackDTO, feedback);

        int status;
        try {
            status = feedbackMapper.add(feedback);
        } catch (DuplicateKeyException e) {
            log.warn("ADD [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", feedbackDTO, e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT, "添加用户反馈失败\n原因: pojoid已存在");
        } catch (Exception e) {
            log.error("ADD [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", feedbackDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加用户反馈失败");
        }
        return status;
    }

    @Override
    public int update(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackDTO, feedback);

        int status;
        try {
            status = feedbackMapper.update(feedback);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [feedback] record not exist");
            }
        }catch (DAOException e){
            log.warn("UPDATE [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", feedbackDTO, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "更新用户反馈失败\n原因: 用户反馈不存在");
        }
        catch (Exception e) {
            log.error("UPDATE [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", feedbackDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "更新用户反馈失败");
        }
        return status;
    }

    @Override
    public int deleteById(String id) {
        int status;
        try {
            status = feedbackMapper.deleteById(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [feedback] record not exist");
            }
        } catch (DAOException e) {
            log.warn("DELETE [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除用户反馈失败\n原因: 用户反馈不存在");
        } catch (Exception e) {
            log.error("DELETE [feedback] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除用户反馈失败");
        }
        return status;
    }

}


