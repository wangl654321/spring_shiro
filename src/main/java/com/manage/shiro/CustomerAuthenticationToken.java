package com.manage.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 *
 *
 * 描    述：
 *
 * 创 建 者：@author wl
 * 创建时间：2019/4/179:08
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String captcha;

    /**
     * 用来区分登录用户的渠道
     */
    private String loginForm;

    public CustomerAuthenticationToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }
}