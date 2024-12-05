package softuni.exam.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "area")
    @Positive
    private double area;

    //Constraint: The countries table has a
    // relation with the attractions table. It cannot be nullable.

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Attraction> attractions;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<Visitor> visitors;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Positive
    public double getArea() {
        return area;
    }

    public void setArea(@Positive double area) {
        this.area = area;
    }

    public Set<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(Set<Attraction> attractions) {
        this.attractions = attractions;
    }

    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }
}
