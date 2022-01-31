package itmo.cosmolabs.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "process")
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean approve;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UnitType> unitTypes;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Document> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public Set<UnitType> getUnitTypes() {
        return unitTypes;
    }

    public void setUnitTypes(Set<UnitType> unitTypes) {
        this.unitTypes = unitTypes;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
