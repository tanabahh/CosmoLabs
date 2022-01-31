package itmo.cosmolabs.repository;

import itmo.cosmolabs.model.GeolocationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeolocationDataRepository extends CrudRepository<GeolocationData, Long>, JpaRepository<GeolocationData, Long> {
}
