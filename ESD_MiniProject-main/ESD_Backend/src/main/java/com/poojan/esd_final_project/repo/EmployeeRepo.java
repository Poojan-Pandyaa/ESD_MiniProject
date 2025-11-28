package com.poojan.esd_final_project.repo;

import com.poojan.esd_final_project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String email);

}
