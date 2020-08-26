package com.wpg.springboot001.moudles.account.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.pojo.Resource;
import com.wpg.springboot001.moudles.account.service.ResourceService;
import com.wpg.springboot001.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/resources",consumes = "application/json")
    public PageInfo<Resource> getResourceBySearchVO(@RequestBody SearchVo searchVo){
        return resourceService.getResourcesBySearchVo(searchVo);
    }
}
