package com.manage.controller;

import com.manage.code.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/***
 *
 *
 * 描    述：登录退出
 *
 * 创 建 者： @author wl
 * 创建时间： 2019/4/4 16:29
 * 创建描述：
 *
 * 修 改 者：
 * 修改时间：
 * 修改描述：
 *
 * 审 核 者：
 * 审核时间：
 * 审核描述：
 *
 */
@Controller
public class LoginController {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "/index";
    }

    /**
     * GET 登录
     *
     * @return
     */
    @RequestMapping(value = "/home")
    public String home() {
        LOGGER.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "/login";
    }

    /**
     * POST 登录 shiro 写法
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Result loginPost(String userName, String password) {

        LOGGER.info("POST请求登录");
        Result result = new Result();
        if (StringUtils.isBlank(userName)) {
            result.setMsg("用户名不能为空");
            return result;
        }
        if (StringUtils.isBlank(password)) {
            result.setMsg("密码不能为空");
            return result;
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (UnknownAccountException e) {
            LOGGER.error("账号不存在");
            result.setMsg("账号不存在");
            return result;
        } catch (DisabledAccountException e) {
            LOGGER.error("账号未启用");
            result.setMsg("账号未启用");
            return result;
        } catch (IncorrectCredentialsException e) {
            LOGGER.error("密码错误");
            result.setMsg("密码错误");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("未知错误,请联系管理员");
            result.setMsg("未知错误,请联系管理员");
            return result;
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 未授权
     *
     * @return
     */
    @RequestMapping(value = "/unAuth")
    public String unAuth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "/login";
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        LOGGER.info("登出");
        Subject subject = SecurityUtils.getSubject();
        Result result = new Result();
        subject.logout();
        result.setSuccess(true);
        return result;
    }
}
