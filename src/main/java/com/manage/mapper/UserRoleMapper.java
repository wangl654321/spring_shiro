package com.manage.mapper;

import java.util.List;

import com.manage.model.UserRole;
import org.springframework.stereotype.Repository;

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
public interface UserRoleMapper {

    int insert(UserRole userRole);

    int updateByPrimaryKeySelective(UserRole userRole);

    List<UserRole> findUserRoleByUserId(Long userId);

    int deleteById(Long id);

    List<Long> findRoleIdListByUserId(Long userId);
}