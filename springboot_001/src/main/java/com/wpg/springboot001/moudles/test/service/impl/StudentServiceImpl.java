package com.wpg.springboot001.moudles.test.service.impl;

import com.wpg.springboot001.moudles.common.vo.Result;
import com.wpg.springboot001.moudles.common.vo.SearchVo;
import com.wpg.springboot001.moudles.test.hibernatedao.StudentHibernatedao;
import com.wpg.springboot001.moudles.test.pojo.Student;
import com.wpg.springboot001.moudles.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentHibernatedao studentHibernatedao;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentHibernatedao.saveAndFlush(student);
        return new Result<Student>(Result.Resultstatus.SUCCESS.status, "insertStudent Success!", student);
    }

    //通过studentId查询学生信息

    @Override
    @Transactional
    public Student getStudentByStudentId(Integer studentId) {
        return studentHibernatedao.findById(studentId).get();
    }

    //分页查询

    @Override
    @Transactional
    public Page<Student> getStudentBySearchVo(SearchVo searchVo) {
        //使用hibernate自己的分页插件
        //获取排序规则
        Sort.Direction direction = "desc".equalsIgnoreCase(searchVo.getSort())
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        //调用本身分页构造一个sort
        Sort sort = new Sort(direction,
                StringUtils.isBlank(searchVo.getOrderBy())
                        ? "studentId" : searchVo.getOrderBy());

        //起始页从0开始
        Pageable pageable = PageRequest.of(searchVo.getCurrentPage() - 1,
                searchVo.getPageSize(), sort);

        //student.setStudentName(searchVo.getKeyWord());对应下面的studentName里的类容
        // build Example对象
        //如果keyWord 为null ,则设置的studentName不参与查询条件
        Student student = new Student();
        student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("studentName", match -> match.contains())
                .withIgnorePaths("studentId");
        Example<Student> example = Example.of(student, matcher);

        return studentHibernatedao.findAll(example, pageable);
    }


    //hibernate提供的模板写的查询方法

    @Override
    @Transactional
    public List<Student> getStudentByStudentName(String studentName) {
        return studentHibernatedao.findByStudentName(studentName);
    }

    //模糊查询所有
    @Override
    @Transactional
    public List<Student> getStudentByStudentNameLike(String studentName) {
        return studentHibernatedao.findByStudentNameLike(String.format("%s%S%s", "%", studentName, "%"));
    }

    //查询所得的前三条
    @Override
    @Transactional
    public List<Student> getStudentByStudentNameLikeTop3(String studentName) {
        return studentHibernatedao.findTop3ByStudentNameLike(String.format("%s%S%s", "%", studentName, "%"));
    }

    //自定义hql查询

    @Override
    @Transactional
    public List<Student> getStudentByStudentNameAndCardId(String studentName, Integer cardid) {
        if (cardid > 0){
            return  studentHibernatedao.getStudentByStudentNameAndCardId_hql(studentName,cardid);
        }else {
            return this.getStudentByStudentNameLike(studentName);
        }

    }
}
