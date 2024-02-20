package com.weihua.race.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weihua.race.common.enums.ResultEnum;
import com.weihua.race.common.exception.DAOException;
import com.weihua.race.common.exception.ResultException;
import com.weihua.race.domain.entity.TrainRecord;
import com.weihua.race.domain.dto.TrainRecordDTO;
import com.weihua.race.domain.vo.TrainRecordVO;
import com.weihua.race.domain.dto.PageDTO;
import com.weihua.race.service.TrainRecordService;
import com.weihua.race.mapper.TrainRecordMapper;
import com.weihua.race.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 针对数据库表[trainRecord]的操作Service
 * </p>
 *
 * @entity {@link TrainRecord}
 * @date 2023-12-21 10:53:03 <br>
 * @version 1.0
 */
@Slf4j
@Service
public class TrainRecordServiceImpl implements TrainRecordService {

    @Resource
    private TrainRecordMapper trainRecordMapper;

    @Override
    public PageInfo<TrainRecordVO> page(PageDTO page, TrainRecordDTO trainRecordDTO) {
        try {
            PageHelper.startPage(page);
            List<TrainRecordVO> trainRecordList = trainRecordMapper.list(trainRecordDTO);
            return new PageInfo<>(trainRecordList);
        } catch (Exception e) {
            log.error("PAGE [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", trainRecordDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询训练记录分页列表失败");
        }
    }

    @Override
    public List<TrainRecordVO> list(TrainRecordDTO trainRecordDTO) {
        try {
            return trainRecordMapper.list(trainRecordDTO);
       } catch (Exception e) {
            log.error("LIST [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", trainRecordDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "查询训练记录列表失败");
        }
    }

    @Override
    public int add(TrainRecordDTO trainRecordDTO) {
        TrainRecord trainRecord = new TrainRecord();
        BeanUtils.copyProperties(trainRecordDTO, trainRecord);

        int status;
        try {
            status = trainRecordMapper.add(trainRecord);
        } catch (DuplicateKeyException e) {
            log.warn("ADD [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", trainRecordDTO, e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT, "添加训练记录失败\n原因: pojoid已存在");
        } catch (Exception e) {
            log.error("ADD [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", trainRecordDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加训练记录失败");
        }
        return status;
    }

    @Override
    public int update(TrainRecordDTO trainRecordDTO) {
        TrainRecord trainRecord = new TrainRecord();
        BeanUtils.copyProperties(trainRecordDTO, trainRecord);

        int status;
        try {
            status = trainRecordMapper.update(trainRecord);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [trainRecord] record not exist");
            }
        }catch (DAOException e){
            log.warn("UPDATE [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", trainRecordDTO, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "更新训练记录失败\n原因: 训练记录不存在");
        }
        catch (Exception e) {
            log.error("UPDATE [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", trainRecordDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "更新训练记录失败");
        }
        return status;
    }

    @Override
    public int deleteById(String id) {
        int status;
        try {
            status = trainRecordMapper.deleteById(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [trainRecord] record not exist");
            }
        } catch (DAOException e) {
            log.warn("DELETE [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除训练记录失败\n原因: 训练记录不存在");
        } catch (Exception e) {
            log.error("DELETE [trainRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除训练记录失败");
        }
        return status;
    }

}


