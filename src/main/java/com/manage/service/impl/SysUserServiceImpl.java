package com.manage.service.impl;

import com.manage.mapper.SysUserMapper;
import com.manage.mapper.UserRoleMapper;
import com.manage.model.SysUser;
import com.manage.model.UserRole;
import com.manage.service.SysUserService;
import com.manage.utils.PageInfo;
import com.manage.vo.SysUserVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class SysUserServiceImpl implements SysUserService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public SysUser findUserByLoginName(String username) {
        return userMapper.findUserByLoginName(username);
    }

    @Override
    public SysUser findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(userMapper.findUserPageCondition(pageInfo));
        pageInfo.setTotal(userMapper.findUserPageCount(pageInfo));
    }

    @Override
    public void addUser(SysUserVo userVo) {
        SysUser user = new SysUser();
        try {
            PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        userMapper.insert(user);

        Long id = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();

        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public void updateUserPwdById(Long userId, String pwd) {
        userMapper.updateUserPwdById(userId, pwd);
    }

    @Override
    public SysUserVo findUserVoById(Long id) {
        return userMapper.findUserVoById(id);
    }

    @Override
    public void updateUser(SysUserVo userVo) {
        SysUser user = new SysUser();
        try {
            PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        userMapper.updateUser(user);
        Long id = userVo.getId();
        List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (UserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }

        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();
        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }

    }

    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteById(id);
        List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (UserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
    }

}
