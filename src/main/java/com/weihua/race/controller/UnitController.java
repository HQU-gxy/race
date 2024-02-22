package com.weihua.race.controller;

import com.weihua.race.domain.entity.Unit;
import com.weihua.race.domain.dto.UnitDTO;
import com.weihua.race.domain.vo.UnitVO;
import com.weihua.race.service.UnitService;
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
 * 单位数据接口
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2024-02-22 14:41:56
 */
@Api(tags = {"单位数据接口"})
@Slf4j
@RestController
@RequestMapping("/api/unit")
public class UnitController extends BaseController {

    @Resource
    private UnitService unitService;

    @ApiOperation(value = "分页条件查询单位列表")
    @GetMapping("/page")
    public ResultVO<PageInfo<UnitVO>> page(PageDTO page, UnitDTO unitDTO) {
        PageInfo<UnitVO> unitPage = unitService.page(page, unitDTO);
        return ResultVOUtil.success(unitPage);
    }

    @ApiOperation(value = "条件查询单位列表")
    @GetMapping("/list")
    public ResultVO<List<UnitVO>> list(UnitDTO unitDTO) {
        List<UnitVO> unitList = unitService.list(unitDTO);
        return ResultVOUtil.success(unitList);
    }

    @ApiOperation(value = "添加单位")
    @PostMapping("/add")
    public ResultVO<UnitDTO> add(@Validated @RequestBody UnitDTO unitDTO) {
        unitService.add(unitDTO);
        return ResultVOUtil.success("添加单位成功");
    }

    @ApiOperation(value = "更新单位")
    @PostMapping("/update")
    public ResultVO<UnitDTO> update(@Validated @RequestBody UnitDTO unitDTO) {
        unitService.update(unitDTO);
        return ResultVOUtil.success("更新单位成功");
    }

    @ApiOperation(value = "删除单位")
    @DeleteMapping("/delete/{id}")
    public ResultVO<UnitDTO> delete(@PathVariable("id") String id) {
        unitService.deleteById(id);
        return ResultVOUtil.success("删除单位成功");
    }

}

