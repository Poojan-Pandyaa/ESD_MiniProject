package com.poojan.esd_final_project.entity;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "alumni_org")
public class AlumniOrganisation {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // FK to organistaion table's org column.
    @ManyToOne
    @JoinColumn(name = "org", referencedColumnName = "org")
    private Organisation organisation;

    // FK to alumni table's PK
    @ManyToOne
    @JoinColumn(name = "alumni_id")
    private Alumni alumni;

    @Column(name = "position")
    private String position;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "leave_date")
    private LocalDate leaveDate;

    public AlumniOrganisation() {
    }

    public AlumniOrganisation(Long id, Organisation organisation, Alumni alumni, String position, LocalDate joinDate,
            LocalDate leaveDate) {
        this.id = id;
        this.organisation = organisation;
        this.alumni = alumni;
        this.position = position;
        this.joinDate = joinDate;
        this.leaveDate = leaveDate;
    }

    public static AlumniOrganisationBuilder builder() {
        return new AlumniOrganisationBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Alumni getAlumni() {
        return alumni;
    }

    public void setAlumni(Alumni alumni) {
        this.alumni = alumni;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public static class AlumniOrganisationBuilder {
        private Long id;
        private Organisation organisation;
        private Alumni alumni;
        private String position;
        private LocalDate joinDate;
        private LocalDate leaveDate;

        AlumniOrganisationBuilder() {
        }

        public AlumniOrganisationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AlumniOrganisationBuilder organisation(Organisation organisation) {
            this.organisation = organisation;
            return this;
        }

        public AlumniOrganisationBuilder alumni(Alumni alumni) {
            this.alumni = alumni;
            return this;
        }

        public AlumniOrganisationBuilder position(String position) {
            this.position = position;
            return this;
        }

        public AlumniOrganisationBuilder joinDate(LocalDate joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public AlumniOrganisationBuilder leaveDate(LocalDate leaveDate) {
            this.leaveDate = leaveDate;
            return this;
        }

        public AlumniOrganisation build() {
            return new AlumniOrganisation(id, organisation, alumni, position, joinDate, leaveDate);
        }

        public String toString() {
            return "AlumniOrganisation.AlumniOrganisationBuilder(id=" + this.id + ", organisation=" + this.organisation
                    + ", alumni=" + this.alumni + ", position=" + this.position + ", joinDate=" + this.joinDate
                    + ", leaveDate=" + this.leaveDate + ")";
        }
    }
}
