package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "organisation")
public class Organisation {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "org", unique = true)
    private String org;

    @OneToMany(mappedBy = "org")
    private List<Placement> placements;

    @OneToMany(mappedBy = "organisation")
    private List<AlumniOrganisation> alumniOrganisations;

    @Column(name = "address")
    private String address;

    public Organisation() {
    }

    public Organisation(Long id, String org, List<Placement> placements, List<AlumniOrganisation> alumniOrganisations,
            String address) {
        this.id = id;
        this.org = org;
        this.placements = placements;
        this.alumniOrganisations = alumniOrganisations;
        this.address = address;
    }

    public static OrganisationBuilder builder() {
        return new OrganisationBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public List<Placement> getPlacements() {
        return placements;
    }

    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }

    public List<AlumniOrganisation> getAlumniOrganisations() {
        return alumniOrganisations;
    }

    public void setAlumniOrganisations(List<AlumniOrganisation> alumniOrganisations) {
        this.alumniOrganisations = alumniOrganisations;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class OrganisationBuilder {
        private Long id;
        private String org;
        private List<Placement> placements;
        private List<AlumniOrganisation> alumniOrganisations;
        private String address;

        OrganisationBuilder() {
        }

        public OrganisationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrganisationBuilder org(String org) {
            this.org = org;
            return this;
        }

        public OrganisationBuilder placements(List<Placement> placements) {
            this.placements = placements;
            return this;
        }

        public OrganisationBuilder alumniOrganisations(List<AlumniOrganisation> alumniOrganisations) {
            this.alumniOrganisations = alumniOrganisations;
            return this;
        }

        public OrganisationBuilder address(String address) {
            this.address = address;
            return this;
        }

        public Organisation build() {
            return new Organisation(id, org, placements, alumniOrganisations, address);
        }

        public String toString() {
            return "Organisation.OrganisationBuilder(id=" + this.id + ", org=" + this.org + ", placements="
                    + this.placements + ", alumniOrganisations=" + this.alumniOrganisations + ", address="
                    + this.address + ")";
        }
    }
}
