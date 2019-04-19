package com.manage.model;

import java.io.Serializable;

/***
 *
*
* 描    述：色对应的资源地址
*
* 创 建 者： @author wl
* 创建时间： 2019/4/19 9:54
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
public class SysRoleModel implements Serializable {

    private static final long serialVersionUID = -1756241579303707517L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 资源地址
     */
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SysRoleModel{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}