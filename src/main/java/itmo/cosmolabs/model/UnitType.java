package itmo.cosmolabs.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "unit_type")
public class UnitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private Set<Process> processes;

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

    public Set<Process> getProcesses() {
        return processes;
    }

    public void setProcess(Set<Process> processes) {
        this.processes = processes;
    }
}
