package com.wpg.springboot001.moudles.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.dao.ResourceDao;
import com.wpg.springboot001.moudles.account.pojo.Resource;
import com.wpg.springboot001.moudles.account.service.ResourceService;
import com.wpg.springboot001.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public PageInfo<Resource> getResourcesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<Resource>(Optional
                .ofNullable(resourceDao.getResourcesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }
}
