package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "specialisation")
public class Specialisation {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "specialisation")
    private List<PlacementFilter> placementFilters;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "credits_req")
    private double creditsReq;

    @Column(name = "year")
    private String year;

    public Specialisation() {
    }

    public Specialisation(Long id, List<PlacementFilter> placementFilters, String code, String name, String description,
            double creditsReq, String year) {
        this.id = id;
        this.placementFilters = placementFilters;
        this.code = code;
        this.name = name;
        this.description = description;
        this.creditsReq = creditsReq;
        this.year = year;
    }

    public static SpecialisationBuilder builder() {
        return new SpecialisationBuilder();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCreditsReq() {
        return creditsReq;
    }

    public void setCreditsReq(double creditsReq) {
        this.creditsReq = creditsReq;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public static class SpecialisationBuilder {
        private Long id;
        private List<PlacementFilter> placementFilters;
        private String code;
        private String name;
        private String description;
        private double creditsReq;
        private String year;

        SpecialisationBuilder() {
        }

        public SpecialisationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SpecialisationBuilder placementFilters(List<PlacementFilter> placementFilters) {
            this.placementFilters = placementFilters;
            return this;
        }

        public SpecialisationBuilder code(String code) {
            this.code = code;
            return this;
        }

        public SpecialisationBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SpecialisationBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SpecialisationBuilder creditsReq(double creditsReq) {
            this.creditsReq = creditsReq;
            return this;
        }

        public SpecialisationBuilder year(String year) {
            this.year = year;
            return this;
        }

        public Specialisation build() {
            return new Specialisation(id, placementFilters, code, name, description, creditsReq, year);
        }

        public String toString() {
            return "Specialisation.SpecialisationBuilder(id=" + this.id + ", placementFilters=" + this.placementFilters
                    + ", code=" + this.code + ", name=" + this.name + ", description=" + this.description
                    + ", creditsReq=" + this.creditsReq + ", year=" + this.year + ")";
        }
    }
}
