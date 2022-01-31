package itmo.cosmolabs.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "unit")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "geo_id")
    private GeolocationData geolocationData;
    @ManyToOne
    @JoinColumn(name = "unit_tupe_id")
    private UnitType unitType;
    private String comment;

    @OneToMany(mappedBy = "unit")
    private Set<Schedule> schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeolocationData getGeo_id() {
        return geolocationData;
    }

    public void setGeolocationData(GeolocationData geolocationData) {
        this.geolocationData = geolocationData;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
