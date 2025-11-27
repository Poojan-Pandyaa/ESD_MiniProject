package com.poojan.esd_final_project.controller;

import com.poojan.esd_final_project.entity.Student;
import com.poojan.esd_final_project.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Object[]>> showAllStudents() {
        return ResponseEntity.ok(studentService.showAllStudents());
    }

    @GetMapping("/{keyword}")
    public List<Object[]> showStudentsByKeyword(@PathVariable String keyword) {
        System.out.println(keyword);
        return studentService.showStudentsByKeyword(keyword);
    }

}
