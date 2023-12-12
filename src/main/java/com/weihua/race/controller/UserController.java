package com.weihua.race.controller;

import com.weihua.race.domain.entity.User;
import com.weihua.race.domain.dto.UserDTO;
import com.weihua.race.domain.vo.UserVO;
import com.weihua.race.service.UserService;
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
 * 用户数据接口
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2023-12-12 15:09:20
 */
@Api(tags = {"用户数据接口"})
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "分页条件查询用户列表")
    @GetMapping("/page")
    public ResultVO<PageInfo<UserVO>> page(PageDTO page, UserDTO userDTO) {
        PageInfo<UserVO> userPage = userService.page(page, userDTO);
        return ResultVOUtil.success(userPage);
    }

    @ApiOperation(value = "条件查询用户列表")
    @GetMapping("/list")
    public ResultVO<List<UserVO>> list(UserDTO userDTO) {
        List<UserVO> userList = userService.list(userDTO);
        return ResultVOUtil.success(userList);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public ResultVO<UserDTO> add(@Validated @RequestBody UserDTO userDTO) {
        userService.add(userDTO);
        return ResultVOUtil.success("添加用户成功");
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("/update")
    public ResultVO<UserDTO> update(@Validated @RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return ResultVOUtil.success("更新用户成功");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public ResultVO<UserDTO> delete(@PathVariable("id") String id) {
        userService.deleteById(id);
        return ResultVOUtil.success("删除用户成功");
    }

}

