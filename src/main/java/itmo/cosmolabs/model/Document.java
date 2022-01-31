package itmo.cosmolabs.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pathInStorage;

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

    public String getPathInStorage() {
        return pathInStorage;
    }

    public void setPathInStorage(String pathInStorage) {
        this.pathInStorage = pathInStorage;
    }
}
