package com.wpg.springboot001.moudles.test.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.common.vo.SearchVo;
import com.wpg.springboot001.moudles.test.pojo.City;
import com.wpg.springboot001.moudles.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     *192.168.18.232/api/cities/522 ---get
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable Integer countryId){
       return cityService.getCitiesByCountryId(countryId);
    }


    /**
     * 192.168.18.232/api/cities/522 ---post
     * {"currentPage":"1","pageSize":"5"}
     */
    @RequestMapping(value = "/cities/{countryId}",consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@PathVariable int countryId, @RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchVo(countryId,searchVo);
    }
}
