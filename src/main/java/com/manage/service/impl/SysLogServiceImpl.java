package com.manage.service.impl;

import com.manage.mapper.SysLogMapper;
import com.manage.model.SysLog;
import com.manage.service.SysLogService;
import com.manage.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 *
*
* 描    述：日志记录
*
* 创 建 者： @author wl
* 创建时间： 2019/4/4 15:40
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
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void insertLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(sysLogMapper.findDataGrid(pageInfo));
        pageInfo.setTotal(sysLogMapper.findDataGridCount(pageInfo));
    }
}
