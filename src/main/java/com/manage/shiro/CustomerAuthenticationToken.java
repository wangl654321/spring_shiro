package com.manage.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/***
 *
 *
 * 描    述：记住密码 remenberMe配置
 *
 * 创 建 者：@author wl
 * 创建时间：2019/4/179:52
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
public class CustomerAuthenticationToken extends UsernamePasswordToken {

    /**
     * 记住密码
     *
     * @param username
     * @param password
     * @param rememberMe
     */
    public CustomerAuthenticationToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }
}