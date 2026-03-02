package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "placement_student")
public class PlacementStudent {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Placement placement;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Student student;

    @Column(name = "cv_app")
    private String cvApp;

    @Column(name = "about")
    private String about;

    @Column(name = "acceptance")
    private String acceptance;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date")
    private LocalDate date;

    public PlacementStudent() {
    }

    public PlacementStudent(Long id, Placement placement, Student student, String cvApp, String about,
            String acceptance, String comments, LocalDate date) {
        this.id = id;
        this.placement = placement;
        this.student = student;
        this.cvApp = cvApp;
        this.about = about;
        this.acceptance = acceptance;
        this.comments = comments;
        this.date = date;
    }

    public static PlacementStudentBuilder builder() {
        return new PlacementStudentBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCvApp() {
        return cvApp;
    }

    public void setCvApp(String cvApp) {
        this.cvApp = cvApp;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static class PlacementStudentBuilder {
        private Long id;
        private Placement placement;
        private Student student;
        private String cvApp;
        private String about;
        private String acceptance;
        private String comments;
        private LocalDate date;

        PlacementStudentBuilder() {
        }

        public PlacementStudentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PlacementStudentBuilder placement(Placement placement) {
            this.placement = placement;
            return this;
        }

        public PlacementStudentBuilder student(Student student) {
            this.student = student;
            return this;
        }

        public PlacementStudentBuilder cvApp(String cvApp) {
            this.cvApp = cvApp;
            return this;
        }

        public PlacementStudentBuilder about(String about) {
            this.about = about;
            return this;
        }

        public PlacementStudentBuilder acceptance(String acceptance) {
            this.acceptance = acceptance;
            return this;
        }

        public PlacementStudentBuilder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public PlacementStudentBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public PlacementStudent build() {
            return new PlacementStudent(id, placement, student, cvApp, about, acceptance, comments, date);
        }

        public String toString() {
            return "PlacementStudent.PlacementStudentBuilder(id=" + this.id + ", placement=" + this.placement
                    + ", student=" + this.student + ", cvApp=" + this.cvApp + ", about=" + this.about + ", acceptance="
                    + this.acceptance + ", comments=" + this.comments + ", date=" + this.date + ")";
        }
    }
}
