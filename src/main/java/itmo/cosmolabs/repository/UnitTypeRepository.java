package itmo.cosmolabs.repository;

import itmo.cosmolabs.model.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTypeRepository extends CrudRepository<UnitType, Long>, JpaRepository<UnitType, Long> {
    public UnitType findUnitTypeById(Long id);
}
