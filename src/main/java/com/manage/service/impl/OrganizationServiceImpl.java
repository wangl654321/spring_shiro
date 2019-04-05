package com.manage.service.impl;

import com.google.common.collect.Lists;
import com.manage.mapper.OrganizationMapper;
import com.manage.model.Organization;
import com.manage.service.OrganizationService;
import com.manage.vo.Tree;
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
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Tree> findTree() {
        List<Tree> trees = Lists.newArrayList();

        List<Organization> organizationFather = organizationMapper.findOrganizationAllByPidNull();

        if (organizationFather != null) {
            for (Organization organizationOne : organizationFather) {
                Tree treeOne = new Tree();

                treeOne.setId(organizationOne.getId());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<Organization> organizationSon = organizationMapper.findOrganizationAllByPid(organizationOne.getId());

                if (organizationSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (Organization organizationTwo : organizationSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(organizationTwo.getId());
                        treeTwo.setText(organizationTwo.getName());
                        treeTwo.setIconCls(organizationTwo.getIcon());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public List<Organization> findTreeGrid() {
        return organizationMapper.findOrganizationAll();
    }

    @Override
    public void addOrganization(Organization organization) {
        organizationMapper.insert(organization);
    }

    @Override
    public Organization findOrganizationById(Long id) {
        return organizationMapper.findOrganizationById(id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationMapper.updateOrganization(organization);
    }

    @Override
    public void deleteOrganizationById(Long id) {
        organizationMapper.deleteOrganizationById(id);
    }

}
