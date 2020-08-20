package com.wpg.springboot001.moudles.test.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
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

    /**
     * 192.168.18.232/api/cities ---post
     * {"currentPage":"1","pageSize":"5","keyWord":"sh","orderBy":"city_name","sort":"asc"}
     */
    @RequestMapping(value = "/cities",consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchVo(searchVo);
    }

    /**
     *192.168.18.232/api/city ---post
     *{"cityName":"wpgzj","countryId":"522","district":"wpgzj"}
     */
    @PostMapping(value = "/city",consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city){
        return cityService.insertcity(city);
    }

    /**
     *192.168.18.232/api/city ---put
     *"cityId"="2258",cityName"="wpg"
     */
    @PutMapping(value = "/city",consumes = "application/x-www-form-urlencoded")
    //使用form接收用@ModelAttribute注解
    public Result<City> updateCity(@ModelAttribute City city){
        return cityService.updateCity(city);
    }

    /**
     *192.168.18.232/api/city/2259 ---post
     */
    @DeleteMapping("/city/{cityId}")
    public Result<City> deleteCity(@PathVariable Integer cityId){
        return cityService.deleteCity(cityId);
    }

}
