package com.manage.controller;

import com.manage.code.Result;
import com.manage.model.SysResource;
import com.manage.model.SysUser;
import com.manage.service.SysResourceService;
import com.manage.vo.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 *
*
* 描    述：资源管理
*
* 创 建 者： @author wl
* 创建时间： 2019/4/8 9:04
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
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private SysResourceService resourceService;

    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        SysUser currentUser = getCurrentUser();
        List<Tree> tree = resourceService.findTree(currentUser);
        return tree;
    }

    /**
     * 资源管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/resource";
    }

    /**
     * 资源管理列表
     *
     * @return
     */
    @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public List<SysResource> treeGrid() {
        List<SysResource> treeGrid = resourceService.findResourceAll();
        return treeGrid;
    }

    /**
     * 添加资源页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "/admin/resourceAdd";
    }

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(SysResource resource) {
        Result result = new Result();
        try {
            resourceService.addResource(resource);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("添加资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 二级资源树
     *
     * @return
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public List<Tree> allTree() {
        return resourceService.findAllTree();
    }

    /**
     * 三级资源树
     *
     * @return
     */
    @RequestMapping(value = "/allTrees", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> allTrees() {
        return resourceService.findAllTrees();
    }

    /**
     * 编辑资源页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        SysResource resource = resourceService.findResourceById(id);
        request.setAttribute("resource", resource);
        return "/admin/resourceEdit";
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(SysResource resource) {
        Result result = new Result();
        try {
            resourceService.updateResource(resource);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("编辑资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            resourceService.deleteResourceById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            logger.error("删除资源失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
