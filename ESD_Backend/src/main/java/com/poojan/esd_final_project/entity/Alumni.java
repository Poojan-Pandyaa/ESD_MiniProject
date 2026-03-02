package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alumni")
public class Alumni {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // sid here is fk referencing sid of students table.
    @ManyToOne
    @JoinColumn(name = "sid")
    private Student student;

    @OneToMany(mappedBy = "alumni")
    private List<AlumniOrganisation> alumniOrganisations;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private long contact;

    public Alumni() {
    }

    public Alumni(Long id, Student student, List<AlumniOrganisation> alumniOrganisations, String email, long contact) {
        this.id = id;
        this.student = student;
        this.alumniOrganisations = alumniOrganisations;
        this.email = email;
        this.contact = contact;
    }

    public static AlumniBuilder builder() {
        return new AlumniBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<AlumniOrganisation> getAlumniOrganisations() {
        return alumniOrganisations;
    }

    public void setAlumniOrganisations(List<AlumniOrganisation> alumniOrganisations) {
        this.alumniOrganisations = alumniOrganisations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public static class AlumniBuilder {
        private Long id;
        private Student student;
        private List<AlumniOrganisation> alumniOrganisations;
        private String email;
        private long contact;

        AlumniBuilder() {
        }

        public AlumniBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AlumniBuilder student(Student student) {
            this.student = student;
            return this;
        }

        public AlumniBuilder alumniOrganisations(List<AlumniOrganisation> alumniOrganisations) {
            this.alumniOrganisations = alumniOrganisations;
            return this;
        }

        public AlumniBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AlumniBuilder contact(long contact) {
            this.contact = contact;
            return this;
        }

        public Alumni build() {
            return new Alumni(id, student, alumniOrganisations, email, contact);
        }

        public String toString() {
            return "Alumni.AlumniBuilder(id=" + this.id + ", student=" + this.student + ", alumniOrganisations="
                    + this.alumniOrganisations + ", email=" + this.email + ", contact=" + this.contact + ")";
        }
    }
}
