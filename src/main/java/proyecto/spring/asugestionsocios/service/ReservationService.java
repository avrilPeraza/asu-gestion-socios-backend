package proyecto.spring.asugestionsocios.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.mapper.UserMapper;
import proyecto.spring.asugestionsocios.model.UserDetailsImpl;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;
import proyecto.spring.asugestionsocios.model.entity.Activity;
import proyecto.spring.asugestionsocios.model.entity.Reservation;
import proyecto.spring.asugestionsocios.model.entity.ReservationStatus;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    public ReservationService(ReservationRepository reservationRepository, UserService userService, UserMapper userMapper){
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public void createReservation(Activity activity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        UserDTO userDTO = userService.getUserById(userDetails.getId());
        User currentUser = userMapper.toEntity(userDTO);

        Reservation reservation = new Reservation();
        reservation.setActivity(activity);
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation.setUser(currentUser);

        reservationRepository.save(reservation);
    }


}
