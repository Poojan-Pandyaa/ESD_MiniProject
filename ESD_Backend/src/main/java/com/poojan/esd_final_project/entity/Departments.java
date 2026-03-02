package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    // @OneToMany(mappedBy = "departments")
    // private List<Employee> employees;

    @Column(name = "capacity")
    private Integer capacity;

    public Departments() {
    }

    public Departments(Long id, String name, Integer capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public static DepartmentsBuilder builder() {
        return new DepartmentsBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public static class DepartmentsBuilder {
        private Long id;
        private String name;
        private Integer capacity;

        DepartmentsBuilder() {
        }

        public DepartmentsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DepartmentsBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepartmentsBuilder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public Departments build() {
            return new Departments(id, name, capacity);
        }

        public String toString() {
            return "Departments.DepartmentsBuilder(id=" + this.id + ", name=" + this.name + ", capacity="
                    + this.capacity + ")";
        }
    }
}
