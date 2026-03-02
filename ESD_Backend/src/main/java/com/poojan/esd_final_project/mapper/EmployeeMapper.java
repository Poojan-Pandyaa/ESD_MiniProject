package com.poojan.esd_final_project.mapper;

import com.poojan.esd_final_project.dto.EmployeeRequest;
import com.poojan.esd_final_project.entity.Departments;
import com.poojan.esd_final_project.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employee toEmployee(EmployeeRequest request) {

        return Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .title(request.title())
                .photographPath(request.photographPath())
                .email(request.email())
                .department(request.departmentName())
                .password(request.password())
                .build();
    }
}
