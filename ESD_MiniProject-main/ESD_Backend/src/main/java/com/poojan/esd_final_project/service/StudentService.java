package com.poojan.esd_final_project.service;

import com.poojan.esd_final_project.dto.StudentPlacementDTO;
import com.poojan.esd_final_project.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<StudentPlacementDTO> showAllStudents() {
        List<Object[]> rawData = studentRepo.showStudentsByKeyword("");
        return convertToDTO(rawData);
    }

    public List<StudentPlacementDTO> showStudentsByKeyword(String keyword) {
        List<Object[]> rawData = studentRepo.showStudentsByKeyword(keyword);
        return convertToDTO(rawData);
    }

    private List<StudentPlacementDTO> convertToDTO(List<Object[]> rawData) {
        return rawData.stream()
                .map(row -> new StudentPlacementDTO(
                        (String) row[0], // firstName
                        (String) row[1], // lastName
                        (String) row[2], // program
                        (String) row[3], // placementOrg
                        (String) row[4], // alumniOrg
                        row[5] != null ? ((Number) row[5]).intValue() : null, // graduationYear
                        (String) row[6], // isAlumni
                        (String) row[7], // placementStatus
                        row[8] != null ? ((Number) row[8]).doubleValue() : null // ctc
                ))
                .collect(Collectors.toList());
    }
}
