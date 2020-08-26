package com.wpg.springboot001.moudles.account.dao;

import com.wpg.springboot001.moudles.account.pojo.Resource;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ResourceDao {

    @Select("<script>" +
            "select * from resource "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (resource_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by resource_id asc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Resource> getResourcesBySearchVo(SearchVo searchVo);

    @Select("select * from resource resource left join role_resource roleResource on "
            + "resource.resource_id = roleResource.resource_id where roleResource.role_id = #{roleId}")
    List<Resource> getResourcesByRoleId(int roleId);
}
