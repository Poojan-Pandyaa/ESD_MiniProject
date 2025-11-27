package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "domains")
public class Domains {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "domain")
    private List<PlacementFilter> placementFilters;

    @OneToMany(mappedBy = "domain")
    private List<Student> students;

    @Column(name = "program")
    private String program;

    @Column(name = "batch")
    private String batch;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "qualification")
    private String qualification;

    public Domains() {
    }

    public Domains(Long id, List<PlacementFilter> placementFilters, List<Student> students, String program,
            String batch, Integer capacity, String qualification) {
        this.id = id;
        this.placementFilters = placementFilters;
        this.students = students;
        this.program = program;
        this.batch = batch;
        this.capacity = capacity;
        this.qualification = qualification;
    }

    public static DomainsBuilder builder() {
        return new DomainsBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PlacementFilter> getPlacementFilters() {
        return placementFilters;
    }

    public void setPlacementFilters(List<PlacementFilter> placementFilters) {
        this.placementFilters = placementFilters;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public static class DomainsBuilder {
        private Long id;
        private List<PlacementFilter> placementFilters;
        private List<Student> students;
        private String program;
        private String batch;
        private Integer capacity;
        private String qualification;

        DomainsBuilder() {
        }

        public DomainsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DomainsBuilder placementFilters(List<PlacementFilter> placementFilters) {
            this.placementFilters = placementFilters;
            return this;
        }

        public DomainsBuilder students(List<Student> students) {
            this.students = students;
            return this;
        }

        public DomainsBuilder program(String program) {
            this.program = program;
            return this;
        }

        public DomainsBuilder batch(String batch) {
            this.batch = batch;
            return this;
        }

        public DomainsBuilder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public DomainsBuilder qualification(String qualification) {
            this.qualification = qualification;
            return this;
        }

        public Domains build() {
            return new Domains(id, placementFilters, students, program, batch, capacity, qualification);
        }

        public String toString() {
            return "Domains.DomainsBuilder(id=" + this.id + ", placementFilters=" + this.placementFilters
                    + ", students=" + this.students + ", program=" + this.program + ", batch=" + this.batch
                    + ", capacity=" + this.capacity + ", qualification=" + this.qualification + ")";
        }
    }
}
