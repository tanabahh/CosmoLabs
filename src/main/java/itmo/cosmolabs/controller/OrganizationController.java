package itmo.cosmolabs.controller;

import itmo.cosmolabs.model.*;
import itmo.cosmolabs.model.Process;
import itmo.cosmolabs.repository.GeolocationDataRepository;
import itmo.cosmolabs.repository.ProcessRepository;
import itmo.cosmolabs.repository.UnitRepository;
import itmo.cosmolabs.repository.UnitTypeRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private final ProcessRepository processRepository;
    private final UnitRepository unitRepository;
    private final UnitTypeRepository unitTypeRepository;
    private final GeolocationDataRepository geolocationDataRepository;

    OrganizationController(ProcessRepository processRepository,
                           UnitRepository unitRepository,
                           UnitTypeRepository unitTypeRepository,
                           GeolocationDataRepository geolocationDataRepository) {
        this.processRepository = processRepository;
        this.unitRepository = unitRepository;
        this.unitTypeRepository = unitTypeRepository;
        this.geolocationDataRepository = geolocationDataRepository;
    }

    @PostMapping("/process-type")
    public Process createProcess(boolean approve, Long unitTypeId, String name) {
        Process process = new Process();
        process.setApprove(approve);
        //process.setDocuments(); unitTypeIds.stream().map(unitTypeRepository::findUnitTypeById)
        //                        .collect(Collectors.toSet())
        process.setUnitTypes(Set.of(unitTypeRepository.findUnitTypeById(unitTypeId)));
        process.setName(name);
        return processRepository.save(process);
    }

    @PostMapping("/unit-type")
    public UnitType createUnitType(String name) {
        UnitType unitType = new UnitType();
        unitType.setName(name);
        return unitTypeRepository.save(unitType);
    }

    @PostMapping("/unit")
    public Unit createUnit(String country, String region, String city, String street, String house, String flat,
                           String zipCode, Long lat, Long lon,
                           Long unitTypeId, String comment) {
        Unit unit = new Unit();
        GeolocationData geolocationData = new GeolocationData();
        geolocationData.setCity(city);
        geolocationData.setCountry(country);
        geolocationData.setStreet(street);
        geolocationData.setHouse(house);
        geolocationData.setFlat(flat);
        geolocationData.setLat(lat);
        geolocationData.setLon(lon);
        geolocationData.setRegion(region);
        geolocationData.setZipCode(zipCode);
        geolocationDataRepository.save(geolocationData);
        unit.setUnitType(unitTypeRepository.findUnitTypeById(unitTypeId));
        unit.setComment(comment);
        unit.setGeolocationData(geolocationData);
        return unitRepository.save(unit);
//        @OneToMany(mappedBy = "unit")
//        private Set<Schedule> schedule;
    }
}
