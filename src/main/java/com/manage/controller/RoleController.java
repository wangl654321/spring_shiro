package com.manage.controller;

import com.google.common.collect.Maps;
import com.manage.code.Result;
import com.manage.model.SysRole;
import com.manage.service.SysRoleService;
import com.manage.utils.PageInfo;
import com.manage.vo.Tree;
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
* 描    述：权限管理
*
* 创 建 者： @author wl
* 创建时间： 2019/4/5 21:03
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
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private SysRoleService roleService;

    /**
     * 权限管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/role";
    }

    /**
     * 权限列表
     *
     * @param role
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(SysRole role, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = Maps.newHashMap();
        pageInfo.setCondition(condition);

        roleService.findDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 权限树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        return roleService.findTree();
    }

    /**
     * 添加权限页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/admin/roleAdd";
    }

    /**
     * 添加权限
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(SysRole role) {
        Result result = new Result();
        try {
            roleService.addRole(role);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("添加角色失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            roleService.deleteRoleById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            logger.error("删除角色失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 编辑权限页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        SysRole role = roleService.findRoleById(id);
        request.setAttribute("role", role);
        return "/admin/roleEdit";
    }

    /**
     * 删除权限
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(SysRole role) {
        Result result = new Result();
        try {
            roleService.updateRole(role);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("编辑角色失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 授权页面
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/grantPage")
    public String grantPage(HttpServletRequest request, Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/roleGrant";
    }

    /**
     * 授权页面页面根据角色查询资源
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Result findResourceByRoleId(HttpServletRequest request, Long id, Model model) {
        Result result = new Result();
        try {
            List<Long> resources = roleService.findResourceIdListByRoleId(id);
            result.setSuccess(true);
            result.setObj(resources);
            return result;
        } catch (RuntimeException e) {
            logger.error("角色查询资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 授权
     *
     * @param id
     * @param resourceIds
     * @return
     */
    @RequestMapping("/grant")
    @ResponseBody
    public Result grant(Long id, String resourceIds) {
        Result result = new Result();
        try {
            roleService.updateRoleResource(id, resourceIds);
            result.setMsg("授权成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            logger.error("授权成功失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
