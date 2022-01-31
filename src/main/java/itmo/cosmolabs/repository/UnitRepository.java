package itmo.cosmolabs.repository;

import itmo.cosmolabs.model.Unit;
import itmo.cosmolabs.model.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long>, JpaRepository<Unit, Long> {
    public List<Unit> findByUnitType(UnitType unitType);
}
