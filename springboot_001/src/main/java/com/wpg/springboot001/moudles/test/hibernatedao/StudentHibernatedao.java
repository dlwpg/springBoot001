package com.wpg.springboot001.moudles.test.hibernatedao;

import com.wpg.springboot001.moudles.test.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface StudentHibernatedao extends JpaRepository<Student, Integer> {

    //自定义查寻：hibernate提供的写法--->通过学生姓名查找
    List<Student> findByStudentName(String studentName);

    //自定义查寻：hibernate提供的写法--->通过学生姓名查找--->模糊查询
    List<Student> findByStudentNameLike(String studentName);

    //自定义查寻：hibernate提供的写法--->通过学生姓名查找--->模糊查询--->显示头三条
    List<Student> findTop3ByStudentNameLike(String studentName);

    //hql自定义查询(传参方式?1,?2或者  :xxx1  :xxx2)
   // @Query(value = "select s from Student s where  s.studentName =:studentName and s.studentCard=:cardid")
    //原生sql写法nativeQuery = true 为放开sql写法
    @Query(nativeQuery = true,value = "select * from  h_student where student_name=:studentName " +
            "and card_id=:cardid")
    List<Student> getStudentByStudentNameAndCardId_hql(@RequestParam("studentName") String studentName,
                                              @RequestParam("cardid") Integer cardid);

}
