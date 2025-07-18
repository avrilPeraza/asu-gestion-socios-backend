package proyecto.spring.asugestionsocios.model.dto.ActividadDTO;

import proyecto.spring.asugestionsocios.model.dto.UsuarioDTO.UsuarioDTO;
import proyecto.spring.asugestionsocios.model.entity.Inscripcion;

import java.time.LocalDate;

public class InscripcionDTO {
    private ActividadDTO actividad;
    private UsuarioDTO usuario;
    private LocalDate fechaInscripcion;
    private Inscripcion inscripcion;
}
