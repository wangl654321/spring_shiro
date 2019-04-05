package com.manage.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

/***
 *
*
* 描    述：分页实体类
*
* 创 建 者： @author wl
* 创建时间： 2019/4/4 15:51
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
public class PageInfo {

    /**
     * 默认显示的记录数
     */
    private final static int PAGE_SIZE = 10;

    /**
     * 总记录
     */
    private int total;

    /**
     * 显示的记录
     */
    private List rows;

    @JsonIgnore
    private int from;

    @JsonIgnore
    private int size;

    /**
     * 当前页
     */
    @JsonIgnore
    private int nowpage;

    /**
     * 每页显示的记录数
     */
    @JsonIgnore
    private int pagesize;

    /**
     * 查询条件
     */
    @JsonIgnore
    private Map<String, Object> condition;

    /**
     * 排序字段
     */
    @JsonIgnore
    private String sort = "seq";

    /**
     * asc，desc mybatis Order 关键字
     */
    @JsonIgnore
    private String order = "asc";

    public PageInfo() {
    }

    /**
     * 构造方法
     * @param nowpage
     * @param pagesize
     */
    public PageInfo(int nowpage, int pagesize) {
        //计算当前页  
        if (nowpage < 0) {
            this.nowpage = 1;
        } else {
            //当前页
            this.nowpage = nowpage;
        }
        //记录每页显示的记录数  
        if (pagesize < 0) {
            this.pagesize = PAGE_SIZE;
        } else {
            this.pagesize = pagesize;
        }
        //计算开始的记录和结束的记录  
        this.from = (this.nowpage - 1) * this.pagesize;
        this.size = this.pagesize;
    }

    /**
     * 构造方法
     * @param nowpage
     * @param pagesize
     * @param sort
     * @param order
     */
    public PageInfo(int nowpage, int pagesize, String sort, String order) {
        // 计算当前页  
        if (nowpage < 0) {
            this.nowpage = 1;
        } else {
            // 当前页
            this.nowpage = nowpage;
        }
        // 记录每页显示的记录数  
        if (pagesize < 0) {
            this.pagesize = PAGE_SIZE;
        } else {
            this.pagesize = pagesize;
        }
        // 计算开始的记录和结束的记录  
        this.from = (this.nowpage - 1) * this.pagesize;
        this.size = this.pagesize;
        // 排序字段，正序还是反序
        this.sort = sort;
        this.order = order;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
