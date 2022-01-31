package itmo.cosmolabs.repository;

import itmo.cosmolabs.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends CrudRepository<Process, Long>, JpaRepository<Process, Long> {
    public Process findProcessById(Long id);
    public Process findProcessByName(String Name);
}
