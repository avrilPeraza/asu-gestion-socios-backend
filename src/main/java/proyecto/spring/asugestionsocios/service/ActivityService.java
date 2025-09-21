package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.ActivityMapper;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityDTO;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.ActivityRepository;
import proyecto.spring.asugestionsocios.repository.ReservationRepository;
import proyecto.spring.asugestionsocios.util.Auditable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final ActivityTypeService activityTypeService;
    private final FacilityService facilityService;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ActivityService(ActivityRepository activityRepository, ActivityMapper activityMapper, ActivityTypeService activityTypeService, FacilityService facilityService, ReservationRepository reservationRepository, ReservationService reservationService){
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
        this.activityTypeService = activityTypeService;
        this.facilityService = facilityService;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @Auditable(operation = "ACTIVITY_BY_ID")
    private Activity findActivityByIdOrThrow(Long id){
        return activityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with ID: "+ id));
    }

    @Auditable(operation = "ACTIVITY_CREATE")
    @Transactional
    public void createActivity(ActivityCreateDTO activityCreateDTO){

        if (activityRepository.existsActivitiesByName(activityCreateDTO.getName())){
            throw new ConflictException("The activity name is already registered");
        }

        ActivityType activityType = activityTypeService.getActivityTypeByIdEntity(activityCreateDTO.getActivityTypeId());

        Facility facility = facilityService.getFacilityByIdEntity(activityCreateDTO.getFacilityId());

        List<Reservation> existingReservations = reservationRepository.findReservationByDateTimeAndFacility(facility.getId(), activityCreateDTO.getDateTime(), activityCreateDTO.getDateTime().plusHours(activityCreateDTO.getDuration()));
        if (!existingReservations.isEmpty()){
            throw new ConflictException("The selected facility is unavailable. There is an existing reservation that overlaps with the chosen date and time.");
        }

        if (facility.getCapacity() < activityCreateDTO.getNumberPeople()){
            throw new ConflictException("The number of participants exceeds the maximum capacity of the selected facility.");
        }

        Activity newActivity = activityMapper.toEntityCreate(activityCreateDTO);
        newActivity.setStatus(Status.INACTIVE);
        newActivity.setActivityType(activityType);
        newActivity.setFacility(facility);
        newActivity.setNumberPeople(activityCreateDTO.getNumberPeople());

        Activity savedActivity = activityRepository.save(newActivity);

        reservationService.createReservation(savedActivity);
    }

    /*public ActivityDTO updateActivity(Long id, ActivityUpdateDTO activityUpdateDTO){

    }*/


    @Auditable(operation = "ACTIVITY_LIST")
    public List<ActivityDTO> getAllActivities(){
        List<Activity> activities = activityRepository.findAll();

        List<ActivityDTO> activityDTOS = new ArrayList<>();
        for (Activity t: activities){
            activityDTOS.add(activityMapper.toDto(t));
        }

        return activityDTOS;
    }

    @Auditable(operation = "ACTIVITY_BY_ID")
    public ActivityDTO getActivityById(Long activityId){
        Activity activity = findActivityByIdOrThrow(activityId);
        return activityMapper.toDto(activity);
    }

    @Auditable(operation = "ACTIVITY_BY_ID")
    public Activity getActivityByIdEntity(Long activityId){
        return findActivityByIdOrThrow(activityId);
    }
}
