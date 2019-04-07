package com.manage.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.manage.code.Result;
import com.manage.model.SysRole;
import com.manage.model.SysUser;
import com.manage.service.SysUserService;
import com.manage.utils.PageInfo;
import com.manage.vo.SysUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/***
 *
*
* 描    述：用户管理
*
* 创 建 者： @author wl
* 创建时间： 2019/4/5 20:31
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
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService userService;

    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/user";
    }

    /**
     * 用户管理列表
     *
     * @param userVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(SysUserVo userVo, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = Maps.newHashMap();

        if (StringUtils.isNoneBlank(userVo.getName())) {
            condition.put("name", userVo.getName());
        }
        if (userVo.getOrganizationId() != null) {
            condition.put("organizationId", userVo.getOrganizationId());
        }
        if (userVo.getCreateDateStart() != null) {
            condition.put("startTime", userVo.getCreateDateStart());
        }
        if (userVo.getCreateDateEnd() != null) {
            condition.put("endTime", userVo.getCreateDateEnd());
        }
        pageInfo.setCondition(condition);
        userService.findDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加用户页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/admin/userAdd";
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(SysUserVo userVo) {
        Result result = new Result();
        SysUser u = userService.findUserByLoginName(userVo.getLoginName());
        if (u != null) {
            result.setMsg("用户名已存在!");
            return result;
        }
        try {
            userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
            userService.addUser(userVo);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("添加用户失败,{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        SysUserVo userVo = userService.findUserVoById(id);
        List<SysRole> rolesList = userVo.getRolesList();
        List<Long> ids = Lists.newArrayList();
        for (SysRole role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "/admin/userEdit";
    }

    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(SysUserVo userVo) {
        Result result = new Result();
        SysUser user = userService.findUserByLoginName(userVo.getLoginName());
        if (user != null && !user.getId().equals(userVo.getId())) {
            result.setMsg("用户名已存在!");
            return result;
        }
        try {
            userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
            userService.updateUser(userVo);
            result.setSuccess(true);
            result.setMsg("修改成功");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("修改用户失败,{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
    public String editPwdPage() {
        return "/admin/userEditPwd";
    }

    /**
     * 修改密码
     *
     * @param request
     * @param oldPwd
     * @param pwd
     * @return
     */
    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Result editUserPwd(HttpServletRequest request, String oldPwd, String pwd) {
        Result result = new Result();
        if (!getCurrentUser().getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
            result.setMsg("旧密码不正确!");
            return result;
        }

        try {
            userService.updateUserPwdById(getUserId(), DigestUtils.md5Hex(pwd));
            result.setSuccess(true);
            result.setMsg("密码修改成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("修改密码失败,{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            userService.deleteUserById(id);
            result.setMsg("删除成功");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("删除用户失败,{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
}
