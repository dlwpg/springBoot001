package com.wpg.springboot001.moudles.account.service;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.pojo.Resource;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.vo.SearchVo;

import java.util.List;

public interface ResourceService {

    PageInfo<Resource> getResourcesBySearchVo(SearchVo searchVo);

    List<Resource> getResourcesByRoleId(int roleId);
}
