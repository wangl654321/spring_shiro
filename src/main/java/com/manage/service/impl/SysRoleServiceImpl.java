package com.manage.service.impl;

import com.google.common.collect.Lists;
import com.manage.mapper.SysRoleMapper;
import com.manage.mapper.RoleResourceMapper;
import com.manage.mapper.UserRoleMapper;
import com.manage.model.SysRole;
import com.manage.model.RoleResource;
import com.manage.model.SysRoleModel;
import com.manage.utils.PageInfo;
import com.manage.vo.Tree;
import com.manage.exception.ServiceException;
import com.manage.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/***
 *
*
* 描    述：
*
* 创 建 者： @author wl
* 创建时间： 2019/4/4 15:36
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
public class SysRoleServiceImpl implements SysRoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(roleMapper.findRolePageCondition(pageInfo));
        pageInfo.setTotal(roleMapper.findRolePageCount(pageInfo));
    }

    @Override
    public List<Tree> findTree() {
        List<Tree> trees = Lists.newArrayList();
        List<SysRole> roles = roleMapper.findRoleAll();
        for (SysRole role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
    }

    @Override
    public void addRole(SysRole role) {
        int insert = roleMapper.insert(role);
        if (insert != 1) {
            LOGGER.warn("插入失败，参数：{}", role.toString());
            throw new ServiceException("插入失败");
        }
    }

    @Override
    public void deleteRoleById(Long id) {
        int update = roleMapper.deleteRoleById(id);
        if (update != 1) {
            LOGGER.warn("删除失败，id：{}", id);
            throw new ServiceException("删除失败");
        }
    }

    @Override
    public SysRole findRoleById(Long id) {
        return roleMapper.findRoleById(id);
    }

    @Override
    public void updateRole(SysRole role) {
        int update = roleMapper.updateRole(role);
        if (update != 1) {
            LOGGER.warn("更新失败，参数：{}", role.toString());
            throw new ServiceException("更新失败");
        }
    }

    @Override
    public List<Long> findResourceIdListByRoleId(Long id) {
        return roleMapper.findResourceIdListByRoleId(id);
    }

    @Override
    public void updateRoleResource(Long id, String resourceIds) {
        // 先删除后添加,有点爆力
        List<Long> roleResourceIdList = roleMapper.findRoleResourceIdListByRoleId(id);
        if (roleResourceIdList != null && (!roleResourceIdList.isEmpty())) {
            for (Long roleResourceId : roleResourceIdList) {
                roleResourceMapper.deleteById(roleResourceId);
            }
        }
        String[] resources = resourceIds.split(",");
        RoleResource roleResource = new RoleResource();
        for (String string : resources) {
            roleResource.setRoleId(id);
            roleResource.setResourceId(Long.parseLong(string));
            roleResourceMapper.insert(roleResource);
        }
    }

    @Override
    public List<Long> findRoleIdListByUserId(Long userId) {
        return userRoleMapper.findRoleIdListByUserId(userId);
    }

    @Override
    public List<SysRoleModel> findRoleResourceListByRoleId(Long roleId) {
        return roleMapper.findRoleResourceListByRoleId(roleId);
    }

}
