package com.weihua.race.controller;

import com.weihua.race.domain.entity.Role;
import com.weihua.race.domain.dto.RoleDTO;
import com.weihua.race.domain.vo.RoleVO;
import com.weihua.race.service.RoleService;
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
 * 系统角色数据接口
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2024-02-22 14:35:14
 */
@Api(tags = {"系统角色数据接口"})
@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "分页条件查询系统角色列表")
    @GetMapping("/page")
    public ResultVO<PageInfo<RoleVO>> page(PageDTO page, RoleDTO roleDTO) {
        PageInfo<RoleVO> rolePage = roleService.page(page, roleDTO);
        return ResultVOUtil.success(rolePage);
    }

    @ApiOperation(value = "条件查询系统角色列表")
    @GetMapping("/list")
    public ResultVO<List<RoleVO>> list(RoleDTO roleDTO) {
        List<RoleVO> roleList = roleService.list(roleDTO);
        return ResultVOUtil.success(roleList);
    }

    @ApiOperation(value = "添加系统角色")
    @PostMapping("/add")
    public ResultVO<RoleDTO> add(@Validated @RequestBody RoleDTO roleDTO) {
        roleService.add(roleDTO);
        return ResultVOUtil.success("添加系统角色成功");
    }

    @ApiOperation(value = "更新系统角色")
    @PostMapping("/update")
    public ResultVO<RoleDTO> update(@Validated @RequestBody RoleDTO roleDTO) {
        roleService.update(roleDTO);
        return ResultVOUtil.success("更新系统角色成功");
    }

    @ApiOperation(value = "删除系统角色")
    @DeleteMapping("/delete/{id}")
    public ResultVO<RoleDTO> delete(@PathVariable("id") String id) {
        roleService.deleteById(id);
        return ResultVOUtil.success("删除系统角色成功");
    }

}

