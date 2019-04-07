package com.manage.mapper;

import com.manage.model.SysResource;
import org.apache.ibatis.annotations.Param;
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
public interface SysResourceMapper {
    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    int insert(SysResource resource);

    /**
     * 修改资源
     *
     * @param resource
     * @return
     */
    int updateResource(SysResource resource);

    /**
     * 查询菜单资源
     *
     * @param resourceType
     * @param pid
     * @return
     */
    List<SysResource> findResourceAllByTypeAndPid(@Param("resourceType") Integer resourceType, @Param("pid") Long pid);

    /**
     * 查询所有资源
     *
     * @return
     */
    List<SysResource> findResourceAll();

    /**
     * 查询一级资源
     *
     * @param resourceMenu
     * @return
     */
    List<SysResource> findResourceAllByTypeAndPidNull(Integer resourceMenu);

    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    SysResource findResourceById(Long id);

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    int deleteResourceById(Long id);
}