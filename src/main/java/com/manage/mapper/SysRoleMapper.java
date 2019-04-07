package com.manage.mapper;

import com.manage.model.SysResource;
import com.manage.model.SysRole;
import com.manage.utils.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 *
 *
 * 描    述：
 *
 * 创 建 者： @author wl
 * 创建时间： 2019/4/4 16:03
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
@Repository
public interface SysRoleMapper {
    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    int insert(SysRole role);

    /**
     * 查询角色列表
     *
     * @param pageInfo
     * @return
     */
    List findRolePageCondition(PageInfo pageInfo);

    /**
     * 角色统计
     *
     * @param pageInfo
     * @return
     */
    int findRolePageCount(PageInfo pageInfo);

    /**
     * 角色列表
     *
     * @return
     */
    List<SysRole> findRoleAll();

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    SysRole findRoleById(Long id);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    int updateRole(SysRole role);

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    int deleteRoleById(Long id);

    /**
     * 根据角色查询资源id列表
     *
     * @param id
     * @return
     */
    List<Long> findResourceIdListByRoleId(Long id);

    /**
     * 根据角色id查询资源角色关联id列表
     *
     * @param
     * @return
     */
    List<Long> findRoleResourceIdListByRoleId(Long id);

    /**
     * 根据角色id查询资源id、链接列表
     *
     * @param id
     * @return
     */
    List<Map<Long, String>> findRoleResourceListByRoleId(Long id);

    /**
     * 查询角色下的菜单列表
     *
     * @param i
     * @return
     */
    List<SysResource> findResourceIdListByRoleIdAndType(Long i);

}