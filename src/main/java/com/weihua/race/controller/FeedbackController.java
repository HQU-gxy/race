package com.weihua.race.controller;

import com.weihua.race.domain.entity.Feedback;
import com.weihua.race.domain.dto.FeedbackDTO;
import com.weihua.race.domain.vo.FeedbackVO;
import com.weihua.race.service.FeedbackService;
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
 * 用户反馈数据接口
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2024-02-22 16:51:35
 */
@Api(tags = {"用户反馈数据接口"})
@Slf4j
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController extends BaseController {

    @Resource
    private FeedbackService feedbackService;

    @ApiOperation(value = "分页条件查询用户反馈列表")
    @GetMapping("/page")
    public ResultVO<PageInfo<FeedbackVO>> page(PageDTO page, FeedbackDTO feedbackDTO) {
        PageInfo<FeedbackVO> feedbackPage = feedbackService.page(page, feedbackDTO);
        return ResultVOUtil.success(feedbackPage);
    }

    @ApiOperation(value = "条件查询用户反馈列表")
    @GetMapping("/list")
    public ResultVO<List<FeedbackVO>> list(FeedbackDTO feedbackDTO) {
        List<FeedbackVO> feedbackList = feedbackService.list(feedbackDTO);
        return ResultVOUtil.success(feedbackList);
    }

    @ApiOperation(value = "添加用户反馈")
    @PostMapping("/add")
    public ResultVO<FeedbackDTO> add(@Validated @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.add(feedbackDTO);
        return ResultVOUtil.success("添加用户反馈成功");
    }

    @ApiOperation(value = "更新用户反馈")
    @PostMapping("/update")
    public ResultVO<FeedbackDTO> update(@Validated @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.update(feedbackDTO);
        return ResultVOUtil.success("更新用户反馈成功");
    }

    @ApiOperation(value = "删除用户反馈")
    @DeleteMapping("/delete/{id}")
    public ResultVO<FeedbackDTO> delete(@PathVariable("id") String id) {
        feedbackService.deleteById(id);
        return ResultVOUtil.success("删除用户反馈成功");
    }

}

