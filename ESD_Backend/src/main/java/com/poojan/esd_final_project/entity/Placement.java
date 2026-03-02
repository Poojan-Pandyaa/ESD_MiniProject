package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "placement")
public class Placement {
        @Id
        // @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        // FK to organisation table,org column.
        @ManyToOne
        @JoinColumn(name = "org", referencedColumnName = "org")
        private Organisation org;

        @OneToMany(mappedBy = "placement")
        private List<PlacementStudent> placementStudents;

        @OneToMany(mappedBy = "placement")
        private List<Student> students;

        @OneToMany(mappedBy = "placement")
        private List<PlacementFilter> placementFilters;

        @Column(name = "profile")
        private String profile;

        @Column(name = "description")
        private String description;

        @Column(name = "intake")
        private int intake;

        @Column(name = "min_grade")
        private double minGrade;

        @Column(name = "ctc")
        private double ctc;

        public Placement() {
        }

        public Placement(Long id, Organisation org, List<PlacementStudent> placementStudents, List<Student> students,
                        List<PlacementFilter> placementFilters, String profile, String description, int intake,
                        double minGrade, double ctc) {
                this.id = id;
                this.org = org;
                this.placementStudents = placementStudents;
                this.students = students;
                this.placementFilters = placementFilters;
                this.profile = profile;
                this.description = description;
                this.intake = intake;
                this.minGrade = minGrade;
                this.ctc = ctc;
        }

        public static PlacementBuilder builder() {
                return new PlacementBuilder();
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Organisation getOrg() {
                return org;
        }

        public void setOrg(Organisation org) {
                this.org = org;
        }

        public List<PlacementStudent> getPlacementStudents() {
                return placementStudents;
        }

        public void setPlacementStudents(List<PlacementStudent> placementStudents) {
                this.placementStudents = placementStudents;
        }

        public List<Student> getStudents() {
                return students;
        }

        public void setStudents(List<Student> students) {
                this.students = students;
        }

        public List<PlacementFilter> getPlacementFilters() {
                return placementFilters;
        }

        public void setPlacementFilters(List<PlacementFilter> placementFilters) {
                this.placementFilters = placementFilters;
        }

        public String getProfile() {
                return profile;
        }

        public void setProfile(String profile) {
                this.profile = profile;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public int getIntake() {
                return intake;
        }

        public void setIntake(int intake) {
                this.intake = intake;
        }

        public double getMinGrade() {
                return minGrade;
        }

        public void setMinGrade(double minGrade) {
                this.minGrade = minGrade;
        }

        public double getCtc() {
                return ctc;
        }

        public void setCtc(double ctc) {
                this.ctc = ctc;
        }

        public static class PlacementBuilder {
                private Long id;
                private Organisation org;
                private List<PlacementStudent> placementStudents;
                private List<Student> students;
                private List<PlacementFilter> placementFilters;
                private String profile;
                private String description;
                private int intake;
                private double minGrade;
                private double ctc;

                PlacementBuilder() {
                }

                public PlacementBuilder id(Long id) {
                        this.id = id;
                        return this;
                }

                public PlacementBuilder org(Organisation org) {
                        this.org = org;
                        return this;
                }

                public PlacementBuilder placementStudents(List<PlacementStudent> placementStudents) {
                        this.placementStudents = placementStudents;
                        return this;
                }

                public PlacementBuilder students(List<Student> students) {
                        this.students = students;
                        return this;
                }

                public PlacementBuilder placementFilters(List<PlacementFilter> placementFilters) {
                        this.placementFilters = placementFilters;
                        return this;
                }

                public PlacementBuilder profile(String profile) {
                        this.profile = profile;
                        return this;
                }

                public PlacementBuilder description(String description) {
                        this.description = description;
                        return this;
                }

                public PlacementBuilder intake(int intake) {
                        this.intake = intake;
                        return this;
                }

                public PlacementBuilder minGrade(double minGrade) {
                        this.minGrade = minGrade;
                        return this;
                }

                public PlacementBuilder ctc(double ctc) {
                        this.ctc = ctc;
                        return this;
                }

                public Placement build() {
                        return new Placement(id, org, placementStudents, students, placementFilters, profile,
                                        description, intake, minGrade, ctc);
                }

                public String toString() {
                        return "Placement.PlacementBuilder(id=" + this.id + ", org=" + this.org + ", placementStudents="
                                        + this.placementStudents + ", students=" + this.students + ", placementFilters="
                                        + this.placementFilters + ", profile=" + this.profile + ", description="
                                        + this.description + ", intake=" + this.intake + ", minGrade=" + this.minGrade
                                        + ", ctc=" + this.ctc + ")";
                }
        }
}
