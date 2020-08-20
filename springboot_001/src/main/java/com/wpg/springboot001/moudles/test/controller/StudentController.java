package com.wpg.springboot001.moudles.test.controller;

import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import com.wpg.springboot001.moudles.test.pojo.Student;
import com.wpg.springboot001.moudles.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 192.168.18.232/api/student
     * {"studentName":""}
     */
    @PostMapping(value = "/student",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }


    /**
     * 192.168.18.232/api/student/1
     */
    @GetMapping(value = "/student/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId){
        return studentService.getStudentByStudentId(studentId);
    }

    /**
     * 192.168.18.232/api/students
     * {"currentPage":"1","pageSize":"5","keyWord":"wpg","orderBy":"studentName","sort":"asc"}
     */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudentBySearchVo(@RequestBody SearchVo searchVo){
        return studentService.getStudentBySearchVo(searchVo);
    }

    /**
     * 姓名精确查询
     * 192.168.18.232/api/student?studentName=wpg1
     * @return
     */
    @GetMapping("/student")
    public List<Student> getStudentByStudentName(@RequestParam String studentName){
       return studentService.getStudentByStudentName(studentName);
    }


    /**
     * 模糊查询
     * 192.168.18.232/api/students?studentName=w
     * @return
     */
    @GetMapping("/students")
    public List<Student> getStudentByStudentNameLike(@RequestParam String studentName ){
        return  studentService.getStudentByStudentNameLike(studentName);
    }

    /**
     * 模糊查询前三条
     * 192.168.18.232/api/studentss?studentName=w
     * @return
     */
    @GetMapping("/studentss")
    public List<Student> getStudentByStudentNameLikeTop3(@RequestParam String studentName ){
        return  studentService.getStudentByStudentNameLikeTop3(studentName);
    }

    /**
     * 自定义 hql多条件查询
     * 192.168.18.232/api/studentsss?studentName=wpg1
     * @return
     */
    @GetMapping(value = "/studentsss")
    public List<Student> getStudentByStudentNameAndCardId(
            @RequestParam("studentName") String studentName,
            @RequestParam(required = false,defaultValue = "0") Integer cardid){
        return  studentService.getStudentByStudentNameAndCardId(studentName,cardid);
    }

}
