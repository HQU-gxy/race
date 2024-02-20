package com.weihua.race.controller;

import com.weihua.race.domain.entity.TrainRecord;
import com.weihua.race.domain.dto.TrainRecordDTO;
import com.weihua.race.domain.vo.TrainRecordVO;
import com.weihua.race.service.TrainRecordService;
import com.weihua.race.utils.ResultVOUtil;
import com.weihua.race.domain.dto.PageDTO;
import com.weihua.race.domain.vo.ResultVO;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 训练记录数据接口
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2023-12-21 10:53:01
 */
@Api(tags = {"训练记录数据接口"})
@Slf4j
@RestController
@RequestMapping("/api/trainRecord")
public class TrainRecordController extends BaseController {

    @Resource
    private TrainRecordService trainRecordService;

    @ApiOperation(value = "分页条件查询训练记录列表")
    @GetMapping("/page")
    public ResultVO<PageInfo<TrainRecordVO>> page(PageDTO page, TrainRecordDTO trainRecordDTO) {
        PageInfo<TrainRecordVO> trainRecordPage = trainRecordService.page(page, trainRecordDTO);
        return ResultVOUtil.success(trainRecordPage);
    }

    @ApiOperation(value = "条件查询训练记录列表")
    @GetMapping("/list")
    public ResultVO<List<TrainRecordVO>> list(TrainRecordDTO trainRecordDTO) {
        List<TrainRecordVO> trainRecordList = trainRecordService.list(trainRecordDTO);
        return ResultVOUtil.success(trainRecordList);
    }

    @ApiOperation(value = "添加训练记录")
    @PostMapping("/add")
    public ResultVO<TrainRecordDTO> add(@Validated @RequestBody TrainRecordDTO trainRecordDTO) {
        trainRecordService.add(trainRecordDTO);
        return ResultVOUtil.success("添加训练记录成功");
    }

    @ApiOperation(value = "更新训练记录")
    @PostMapping("/update")
    public ResultVO<TrainRecordDTO> update(@Validated @RequestBody TrainRecordDTO trainRecordDTO) {
        trainRecordService.update(trainRecordDTO);
        return ResultVOUtil.success("更新训练记录成功");
    }

    @ApiOperation(value = "删除训练记录")
    @DeleteMapping("/delete/{id}")
    public ResultVO<TrainRecordDTO> delete(@PathVariable("id") String id) {
        trainRecordService.deleteById(id);
        return ResultVOUtil.success("删除训练记录成功");
    }

}

