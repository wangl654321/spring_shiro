package com.manage.service;

import com.manage.model.SysResource;
import com.manage.model.SysUser;
import com.manage.vo.Tree;

import java.util.List;

/**
 * @description：资源管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public interface SysResourceService {

    /**
     * 根据用户查询树形菜单列表
     *
     * @param currentUser
     * @return
     */
    List<Tree> findTree(SysUser currentUser);

    /**
     * 查询所有资源
     *
     * @return
     */
    List<SysResource> findResourceAll();

    /**
     * 添加资源
     *
     * @param resource
     */
    void addResource(SysResource resource);

    /**
     * 查询二级数
     *
     * @return
     */
    List<Tree> findAllTree();

    /**
     * 查询三级数
     *
     * @return
     */
    List<Tree> findAllTrees();

    /**
     * 更新资源
     *
     * @param resource
     */
    void updateResource(SysResource resource);

    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    SysResource findResourceById(Long id);

    /**
     * 根据id删除资源
     *
     * @param id
     */
    void deleteResourceById(Long id);

}
