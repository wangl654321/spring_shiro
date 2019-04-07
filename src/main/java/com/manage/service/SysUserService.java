package com.manage.service;

import com.manage.model.SysUser;
import com.manage.utils.PageInfo;
import com.manage.vo.SysUserVo;

/***
 *
*
* 描    述：用户管理
*
* 创 建 者： @author wl
* 创建时间： 2019/4/5 20:22
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
public interface SysUserService {
    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    SysUser findUserByLoginName(String userName);

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
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加用户
     *
     * @param userVo
     */
    void addUser(SysUserVo userVo);

    /**
     * 修改密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(Long userId, String pwd);

    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    SysUserVo findUserVoById(Long id);

    /**
     * 修改用户
     *
     * @param userVo
     */
    void updateUser(SysUserVo userVo);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(Long id);

}
