package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioEditDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String calle;
    private String numPuerta;
    private String apartamento;
    private Long perfilId;
    private String contrasenia;
    private Boolean usoLenguajeSenia = false;
    private Boolean difAuditiva = false;
    private Long subcomisionId;
}
