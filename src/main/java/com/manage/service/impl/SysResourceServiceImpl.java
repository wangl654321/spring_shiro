package com.manage.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.manage.mapper.SysResourceMapper;
import com.manage.mapper.SysRoleMapper;
import com.manage.mapper.UserRoleMapper;
import com.manage.model.SysResource;
import com.manage.model.SysUser;
import com.manage.utils.Config;
import com.manage.vo.Tree;
import com.manage.service.SysResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/***
 *
*
* 描    述：
*
* 创 建 者： @author wl
* 创建时间： 2019/4/4 15:35
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
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SysResourceServiceImpl implements SysResourceService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysResourceServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<Tree> findTree(SysUser user) {
        List<Tree> trees = Lists.newArrayList();
        // 超级管理
        if (user.getLoginName().equals("admin")) {
            List<SysResource> resourceFather = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
            if (resourceFather == null) {
                return null;
            }

            for (SysResource resourceOne : resourceFather) {
                Tree treeOne = new Tree();

                treeOne.setId(resourceOne.getId());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                List<SysResource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

                if (resourceSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (SysResource resourceTwo : resourceSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
            return trees;
        }

        // 普通用户
        Set<SysResource> resourceIdList = Sets.newHashSet();
        List<Long> roleIdList = userRoleMapper.findRoleIdListByUserId(user.getId());
        for (Long i : roleIdList) {
            List<SysResource> resourceIdLists = roleMapper.findResourceIdListByRoleIdAndType(i);
            for (SysResource resource: resourceIdLists) {
                resourceIdList.add(resource);
            }
        }
        for (SysResource resource : resourceIdList) {
                if (resource != null && resource.getPid() == null) {
                    Tree treeOne = new Tree();
                    treeOne.setId(resource.getId());
                    treeOne.setText(resource.getName());
                    treeOne.setIconCls(resource.getIcon());
                    treeOne.setAttributes(resource.getUrl());
                    List<Tree> tree = Lists.newArrayList();
                    for (SysResource resourceTwo : resourceIdList) {
                        if (resourceTwo.getPid() != null && resource.getId().longValue() == resourceTwo.getPid().longValue()) {
                            Tree treeTwo = new Tree();
                            treeTwo.setId(resourceTwo.getId());
                            treeTwo.setText(resourceTwo.getName());
                            treeTwo.setIconCls(resourceTwo.getIcon());
                            treeTwo.setAttributes(resourceTwo.getUrl());
                            tree.add(treeTwo);
                        }
                    }
                    treeOne.setChildren(tree);
                    trees.add(treeOne);
                }
        }
        return trees;
    }

    @Override
    public List<SysResource> findResourceAll() {
        return resourceMapper.findResourceAll();
    }

    @Override
    public void addResource(SysResource resource) {
        resourceMapper.insert(resource);
    }

    @Override
    public List<Tree> findAllTree() {
        List<Tree> trees = Lists.newArrayList();
        // 查询所有的一级树
        List<SysResource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }
        for (SysResource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            // 查询所有一级树下的菜单
            List<SysResource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

            if (resourceSon != null) {
                List<Tree> tree = Lists.newArrayList();
                for (SysResource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();
                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());
                    tree.add(treeTwo);
                }
                treeOne.setChildren(tree);
            } else {
                treeOne.setState("closed");
            }
            trees.add(treeOne);
        }
        return trees;
    }

    @Override
    public List<Tree> findAllTrees() {
        List<Tree> treeOneList = Lists.newArrayList();

        // 查询所有的一级树
        List<SysResource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Config.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }

        for (SysResource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());

            List<SysResource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_MENU, resourceOne.getId());

            if (resourceSon == null) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = Lists.newArrayList();

                for (SysResource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());

                    /***************************************************/
                    List<SysResource> resourceSons = resourceMapper.findResourceAllByTypeAndPid(Config.RESOURCE_BUTTON, resourceTwo.getId());

                    if (resourceSons == null) {
                        treeTwo.setState("closed");
                    } else {
                        List<Tree> treeThreeList = Lists.newArrayList();

                        for (SysResource resourceThree : resourceSons) {
                            Tree treeThree = new Tree();

                            treeThree.setId(resourceThree.getId());
                            treeThree.setText(resourceThree.getName());
                            treeThree.setIconCls(resourceThree.getIcon());
                            treeThree.setAttributes(resourceThree.getUrl());

                            treeThreeList.add(treeThree);
                        }
                        treeTwo.setChildren(treeThreeList);
                    }
                    /***************************************************/

                    treeTwoList.add(treeTwo);
                }
                treeOne.setChildren(treeTwoList);
            }

            treeOneList.add(treeOne);
        }
        return treeOneList;
    }

    @Override
    public void updateResource(SysResource resource) {
        int update = resourceMapper.updateResource(resource);
        if (update != 1) {
            throw new RuntimeException("更新失败");
        }
    }

    @Override
    public SysResource findResourceById(Long id) {
        return resourceMapper.findResourceById(id);
    }

    @Override
    public void deleteResourceById(Long id) {
        int delete = resourceMapper.deleteResourceById(id);
        if (delete != 1) {
            throw new RuntimeException("删除失败");
        }
    }

    public static void main(String[] args) {
        Long abc = 126L;
        Long def = 126L;
        System.out.println(abc.equals(def));
    }

}
