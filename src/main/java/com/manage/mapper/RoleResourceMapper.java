package com.manage.mapper;

import com.manage.model.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface RoleResourceMapper {
    /**
     * 添加角色资源关联
     *
     * @param roleResource
     * @return
     */
    int insert(RoleResource roleResource);

    /**
     * 根据角色id查询角色资源关联列表
     *
     * @param id
     * @return
     */
    List<RoleResource> findRoleResourceIdListByRoleId(Long id);

    /**
     * 删除角色资源关联关系
     *
     * @param roleResourceId
     * @return
     */
    int deleteById(Long roleResourceId);
}