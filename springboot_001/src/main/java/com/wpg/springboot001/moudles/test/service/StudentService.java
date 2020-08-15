package com.wpg.springboot001.moudles.test.service;

import com.wpg.springboot001.moudles.common.vo.Result;
import com.wpg.springboot001.moudles.common.vo.SearchVo;
import com.wpg.springboot001.moudles.test.pojo.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Result<Student> insertStudent(Student student);

    Student getStudentByStudentId(Integer studentId);

    //分页查询
    Page<Student> getStudentBySearchVo(SearchVo searchVo);

    //1.通过学生姓名，hibernate提供的模板写的方法
    List<Student> getStudentByStudentName(String studentName);

    //2.模糊查询所有
    List<Student> getStudentByStudentNameLike(String studentName);

    //3.模糊查询所有中的前三条
    List<Student> getStudentByStudentNameLikeTop3(String studentName);


    //自定义hql多条件查询
    List<Student> getStudentByStudentNameAndCardId(String  studentName,Integer cardid);

}
