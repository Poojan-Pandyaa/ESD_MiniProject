package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "title")
    private String title;

    @Column(name = "photograph_path")
    private String photographPath;

    // @ManyToOne
    // @JoinColumn(name = "department",referencedColumnName = "name")
    // private Departments department;
    @Column(name = "department")
    private String department;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String title, String photographPath, String department,
            String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.photographPath = photographPath;
        this.department = department;
        this.email = email;
        this.password = password;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotographPath() {
        return photographPath;
    }

    public void setPhotographPath(String photographPath) {
        this.photographPath = photographPath;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class EmployeeBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String title;
        private String photographPath;
        private String department;
        private String email;
        private String password;

        EmployeeBuilder() {
        }

        public EmployeeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder title(String title) {
            this.title = title;
            return this;
        }

        public EmployeeBuilder photographPath(String photographPath) {
            this.photographPath = photographPath;
            return this;
        }

        public EmployeeBuilder department(String department) {
            this.department = department;
            return this;
        }

        public EmployeeBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Employee build() {
            return new Employee(id, firstName, lastName, title, photographPath, department, email, password);
        }

        public String toString() {
            return "Employee.EmployeeBuilder(id=" + this.id + ", firstName=" + this.firstName + ", lastName="
                    + this.lastName + ", title=" + this.title + ", photographPath=" + this.photographPath
                    + ", department=" + this.department + ", email=" + this.email + ", password=" + this.password + ")";
        }
    }
}
