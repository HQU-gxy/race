package com.weihua.race.controller;


import com.weihua.race.common.enums.ResultEnum;
import com.weihua.race.common.exception.ResultException;
import com.weihua.race.domain.bean.UserTokenInfo;
import com.weihua.race.domain.entity.User;
import com.weihua.race.domain.vo.ResultVO;
import com.weihua.race.domain.vo.UserVO;
import com.weihua.race.service.UserService;
import com.weihua.race.utils.JWTUtil;
import com.weihua.race.utils.ResultVOUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/")
public class LoginController {

    @Resource
    private UserService userService;

    private @NotNull(message = "用户名不能为空") String username;
    private @NotNull(message = "密码不能为空") String password;
    private ArrayList<String> roles;

    @ApiOperation(value = "未登陆时跳转的接口")
    @GetMapping("/login")
    public ResultVO toLogin() {
        return ResultVOUtil.error(ResultEnum.USER_LOGOUT);
    }

    @ApiOperation(value = "未授权时跳转的接口")
    @GetMapping("/unauthorized")
    public ResultVO unAuthorized() {
        return ResultVOUtil.error(ResultEnum.NO_PERMISSIONS);
    }

    @ApiOperation(value = "登录", notes = "json对象中只需要有账号和密码")
    @PostMapping("/login")
    public ResultVO login(@RequestBody User user) {
        username = user.getUsername();
        password = user.getPassword();
        if (username == null || password == null) {
            throw new ResultException(ResultEnum.USER_NAME_PWD_NULL);
        }
        User currentUser = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            currentUser = (User) subject.getPrincipal();
            UserTokenInfo userTokenInfo = new UserTokenInfo();
            BeanUtils.copyProperties(currentUser,userTokenInfo);
            // String jwtToken = JWTUtil.createToken(currentUser);
            String jwtToken = JWTUtil.createToken(userTokenInfo);

            HashMap<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("token", jwtToken);

            return ResultVOUtil.success("登录成功", dataMap);
        } catch (IncorrectCredentialsException e) {
            return ResultVOUtil.error(ResultEnum.USER_NAME_PWD_ERROR);
        }
    }

    @GetMapping("/get-info")
    public ResultVO<UserVO> getInfo(String token) {
        String username = JWTUtil.getUsername(token);
        User user = userService.getUserByUsername(username);

        UserVO userInfo = new UserVO();

        BeanUtils.copyProperties(user, userInfo);
        roles = new ArrayList<>();
        roles.add(userInfo.getRoleId());

        log.info(userInfo.toString());

        return ResultVOUtil.success(userInfo);

    }

    @PostMapping("/logout")
    public ResultVO logout() {
        return ResultVOUtil.success("退出登录成功");
    }

}
