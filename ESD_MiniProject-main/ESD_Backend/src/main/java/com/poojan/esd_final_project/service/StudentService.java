package com.poojan.esd_final_project.service;

import com.poojan.esd_final_project.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Object[]> showAllStudents() {
        // Use the keyword query with empty string to get all students in 8-column
        // format
        return studentRepo.showStudentsByKeyword("");
    }

    public List<Object[]> showStudentsByKeyword(String keyword) {
        List<Object[]> objects = studentRepo.showStudentsByKeyword(keyword);
        return objects;
    }
}
