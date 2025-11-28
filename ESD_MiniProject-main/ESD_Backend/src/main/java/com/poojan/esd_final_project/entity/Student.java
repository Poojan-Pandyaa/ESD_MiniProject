package com.poojan.esd_final_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "student")
    private List<Alumni> alumni;

    @OneToMany(mappedBy = "student")
    private List<PlacementStudent> placementStudent;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "rollno", unique = true, nullable = false)
    private String rollno;

    @Column(name = "cgpa")
    private int cgpa;

    @Column(name = "graduation_year")
    private int gradYear;

    @Column(name = "total_credits")
    private double credits;

    @Column(name = "org")
    private String organization;

    @ManyToOne
    @JoinColumn(name = "domain")
    private Domains domain;

    @ManyToOne
    @JoinColumn(name = "specialisation")
    private Specialisation specialisation;

    // placement_id FK.
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Placement placement;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "ctc")
    private Double ctc;

    public Student() {
    }

    public Student(Long id, List<Alumni> alumni, List<PlacementStudent> placementStudent, String firstName,
            String lastName, String email, String rollno, int cgpa, int gradYear, double credits, String organization,
            Domains domain, Specialisation specialisation, Placement placement, String photoPath, Double ctc) {
        this.id = id;
        this.alumni = alumni;
        this.placementStudent = placementStudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rollno = rollno;
        this.cgpa = cgpa;
        this.gradYear = gradYear;
        this.credits = credits;
        this.organization = organization;
        this.domain = domain;
        this.specialisation = specialisation;
        this.placement = placement;
        this.photoPath = photoPath;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Alumni> getAlumni() {
        return alumni;
    }

    public void setAlumni(List<Alumni> alumni) {
        this.alumni = alumni;
    }

    public List<PlacementStudent> getPlacementStudent() {
        return placementStudent;
    }

    public void setPlacementStudent(List<PlacementStudent> placementStudent) {
        this.placementStudent = placementStudent;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public int getCgpa() {
        return cgpa;
    }

    public void setCgpa(int cgpa) {
        this.cgpa = cgpa;
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Domains getDomain() {
        return domain;
    }

    public void setDomain(Domains domain) {
        this.domain = domain;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public static class StudentBuilder {
        private Long id;
        private List<Alumni> alumni;
        private List<PlacementStudent> placementStudent;
        private String firstName;
        private String lastName;
        private String email;
        private String rollno;
        private int cgpa;
        private int gradYear;
        private double credits;
        private String organization;
        private Domains domain;
        private Specialisation specialisation;
        private Placement placement;
        private Placement placement;
        private String photoPath;
        private Double ctc;

        StudentBuilder() {
        }

        public StudentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StudentBuilder alumni(List<Alumni> alumni) {
            this.alumni = alumni;
            return this;
        }

        public StudentBuilder placementStudent(List<PlacementStudent> placementStudent) {
            this.placementStudent = placementStudent;
            return this;
        }

        public StudentBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public StudentBuilder email(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder rollno(String rollno) {
            this.rollno = rollno;
            return this;
        }

        public StudentBuilder cgpa(int cgpa) {
            this.cgpa = cgpa;
            return this;
        }

        public StudentBuilder gradYear(int gradYear) {
            this.gradYear = gradYear;
            return this;
        }

        public StudentBuilder credits(double credits) {
            this.credits = credits;
            return this;
        }

        public StudentBuilder organization(String organization) {
            this.organization = organization;
            return this;
        }

        public StudentBuilder domain(Domains domain) {
            this.domain = domain;
            return this;
        }

        public StudentBuilder specialisation(Specialisation specialisation) {
            this.specialisation = specialisation;
            return this;
        }

        public StudentBuilder placement(Placement placement) {
            this.placement = placement;
            return this;
        }

        public StudentBuilder photoPath(String photoPath) {
            this.photoPath = photoPath;
            return this;
        }

        public StudentBuilder ctc(Double ctc) {
            this.ctc = ctc;
            return this;
        }

        public Student build() {
            return new Student(id, alumni, placementStudent, firstName, lastName, email, rollno, cgpa, gradYear,
                    credits, organization, domain, specialisation, placement, photoPath, ctc);
        }

        public String toString() {
            return "Student.StudentBuilder(id=" + this.id + ", alumni=" + this.alumni + ", placementStudent="
                    + this.placementStudent + ", firstName=" + this.firstName + ", lastName=" + this.lastName
                    + ", email=" + this.email + ", rollno=" + this.rollno + ", cgpa=" + this.cgpa + ", gradYear="
                    + this.gradYear + ", credits=" + this.credits + ", organization=" + this.organization + ", domain="
                    + this.domain + ", specialisation=" + this.specialisation + ", placement=" + this.placement
                    + ", photoPath=" + this.photoPath + ", ctc=" + this.ctc + ")";
        }
    }
}
