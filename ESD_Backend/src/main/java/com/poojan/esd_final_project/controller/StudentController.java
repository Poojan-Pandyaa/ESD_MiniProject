package com.poojan.esd_final_project.controller;

import com.poojan.esd_final_project.dto.StudentPlacementDTO;
import com.poojan.esd_final_project.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentPlacementDTO>> showAllStudents() {
        return ResponseEntity.ok(studentService.showAllStudents());
    }

    @GetMapping("/{keyword}")
    public List<StudentPlacementDTO> showStudentsByKeyword(@PathVariable String keyword) {
        System.out.println(keyword);
        return studentService.showStudentsByKeyword(keyword);
    }

}
