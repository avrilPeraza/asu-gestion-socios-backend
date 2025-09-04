package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.FacilityMapper;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityStatusChangeDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityUpdateDTO;
import proyecto.spring.asugestionsocios.model.entity.Facility;
import proyecto.spring.asugestionsocios.model.entity.Status;
import proyecto.spring.asugestionsocios.repository.FacilityRepository;
import proyecto.spring.asugestionsocios.util.Auditable;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;

    public FacilityService(FacilityRepository facilityRepository, FacilityMapper facilityMapper){
        this.facilityRepository = facilityRepository;
        this.facilityMapper = facilityMapper;
    }

    private Facility findFacilityByIdOrThrow(Long id){
        return facilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with ID: "+ id));
    }

    public void createFacility(FacilityCreateDTO facilityCreateDTO){
        if (facilityRepository.existsFacilityByName(facilityCreateDTO.getName())){
            throw new ConflictException("The facility name is already registered");
        }

        Facility newFacility = facilityMapper.toEntityCreate(facilityCreateDTO);
        newFacility.setStatus(Status.INACTIVE);

        facilityRepository.save(newFacility);
    }

    public FacilityDTO updateFacility(Long id, FacilityUpdateDTO facilityUpdateDTO){
        Facility existingFacility = findFacilityByIdOrThrow(id);

        existingFacility.setDescription(facilityUpdateDTO.getDescription());
        existingFacility.setStatus(facilityUpdateDTO.getStatus());
        existingFacility.setCapacity(facilityUpdateDTO.getCapacity());
        existingFacility.setMemberRate(facilityUpdateDTO.getMemberRate());
        existingFacility.setNonMemberRate(facilityUpdateDTO.getNonMemberRate());
        existingFacility.setObservations(facilityUpdateDTO.getObservations());

        Facility facilityUpdated = facilityRepository.save(existingFacility);

        return facilityMapper.toDto(facilityUpdated);
    }

    @Auditable(operation = "FACILITIES_UPDATE_STATUS")
    public String updateFacilityStatus(Long id, FacilityStatusChangeDTO facilityStatusChangeDTO){
        Facility facility = findFacilityByIdOrThrow(id);

        if (facility.getStatus().name().equals(facilityStatusChangeDTO.getNewStatus().name())){
            throw new ConflictException("Facility is already " + facility.getStatus().name());
        }

        facility.setStatus(facilityStatusChangeDTO.getNewStatus());

        Facility facilityUpdated = facilityRepository.save(facility);

        switch (facility.getStatus()){
            case ACTIVE -> {
                return "Facility has been successfully activated.";
            }
            case INACTIVE -> {
                return "Facility has been successfully deactivated.";
            }
            default -> throw new IllegalArgumentException("Invalid facility status: " + facilityUpdated.getStatus());
        }
    }

    public List<FacilityDTO> getAllFacilities(){
        List<Facility> facilities = facilityRepository.findAll();

        List<FacilityDTO> facilityDTOS = new ArrayList<>();
        for (Facility f: facilities){
            facilityDTOS.add(facilityMapper.toDto(f));
        }

        return facilityDTOS;
    }

    @Auditable(operation = "FACILITY_BY_ID")
    public FacilityDTO getFacilityById(Long facilityId){
        Facility facility =  findFacilityByIdOrThrow(facilityId);
        return facilityMapper.toDto(facility);
    }

    @Auditable(operation = "FACILITY_BY_ID")
    public Facility getFacilityByIdEntity(Long facilityId){
        return findFacilityByIdOrThrow(facilityId);
    }
}
