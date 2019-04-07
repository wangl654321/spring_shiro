package com.manage.mapper;

import com.manage.model.SysUser;
import com.manage.utils.PageInfo;
import com.manage.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 *
 *
 * 描    述：
 *
 * 创 建 者： @author wl
 * 创建时间： 2019/4/4 16:04
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
public interface SysUserMapper {
    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insert(SysUser user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(SysUser user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser findUserByLoginName(String username);

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    SysUser findUserById(Long id);

    /**
     * 用户列表
     *
     * @param pageInfo
     * @return
     */
    List findUserPageCondition(PageInfo pageInfo);

    /**
     * 统计用户
     *
     * @param pageInfo
     * @return
     */
    int findUserPageCount(PageInfo pageInfo);

    /**
     * 修改用户密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(@Param("userId") Long userId, @Param("pwd") String pwd);

    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    SysUserVo findUserVoById(Long id);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    SysUser selectByPrimaryKey(Long id);
}