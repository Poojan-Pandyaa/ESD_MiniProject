package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "placememt_filter")
public class PlacementFilter {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Placement placement;

    @ManyToOne
    @JoinColumn(name = "specialisation")
    private Specialisation specialisation;

    @ManyToOne
    @JoinColumn(name = "domain")
    private Domains domain;

    public PlacementFilter() {
    }

    public PlacementFilter(Long id, Placement placement, Specialisation specialisation, Domains domain) {
        this.id = id;
        this.placement = placement;
        this.specialisation = specialisation;
        this.domain = domain;
    }

    public static PlacementFilterBuilder builder() {
        return new PlacementFilterBuilder();
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

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    public Domains getDomain() {
        return domain;
    }

    public void setDomain(Domains domain) {
        this.domain = domain;
    }

    public static class PlacementFilterBuilder {
        private Long id;
        private Placement placement;
        private Specialisation specialisation;
        private Domains domain;

        PlacementFilterBuilder() {
        }

        public PlacementFilterBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PlacementFilterBuilder placement(Placement placement) {
            this.placement = placement;
            return this;
        }

        public PlacementFilterBuilder specialisation(Specialisation specialisation) {
            this.specialisation = specialisation;
            return this;
        }

        public PlacementFilterBuilder domain(Domains domain) {
            this.domain = domain;
            return this;
        }

        public PlacementFilter build() {
            return new PlacementFilter(id, placement, specialisation, domain);
        }

        public String toString() {
            return "PlacementFilter.PlacementFilterBuilder(id=" + this.id + ", placement=" + this.placement
                    + ", specialisation=" + this.specialisation + ", domain=" + this.domain + ")";
        }
    }
}
