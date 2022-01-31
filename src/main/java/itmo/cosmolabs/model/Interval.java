package itmo.cosmolabs.model;


import javax.persistence.*;

@Entity
@Table(name = "interval")
public class Interval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startInterval;
    private String endInterval;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(String startInterval) {
        this.startInterval = startInterval;
    }

    public String getEndInterval() {
        return endInterval;
    }

    public void setEndInterval(String endInterval) {
        this.endInterval = endInterval;
    }
}
