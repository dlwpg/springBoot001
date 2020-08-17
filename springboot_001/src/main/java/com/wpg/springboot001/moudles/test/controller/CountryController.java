package com.wpg.springboot001.moudles.test.controller;

import com.wpg.springboot001.moudles.test.pojo.Country;
import com.wpg.springboot001.moudles.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private CountryService countryService;

    /**
     * 192.168.18.232/api/country/522  ---get
     */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable Integer countryId){
        Country country=countryService.getCountryByCountryId(countryId);
        System.out.println(country);
        return country;
    }


    /**
     * 192.168.18.232/api/country?countryName=China  ---get
     */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){
        Country country=countryService.getCountryByCountryName(countryName);
        return country;
    }

    /**
     *192.168.18.232/api/redis/522---get
     * by redis
     */
    @GetMapping("/redis/{countryId}")
    public Country getCountryByRedis(@PathVariable Integer countryId){
      return  countryService.getCountryByredis(countryId);
    }
}
