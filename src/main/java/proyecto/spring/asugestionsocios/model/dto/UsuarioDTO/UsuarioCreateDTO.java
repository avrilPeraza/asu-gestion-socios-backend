package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.TipoDocumento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioCreateDTO {
    private String nombres;
    private String apellidos;
    private TipoDocumento tipoDocumento;
    private String documento;
    private LocalDate fechaNacimiento;
    private String calle;
    private String numPuerta;
    private String apartamento;
    private String email;
    private String contrasenia;
    private Long perfilId;
    private List<TelefonoCreateDTO> telefonos = new ArrayList<>();
    private Boolean usoLenguajeSenia = false;
    private Boolean difAuditiva = false;
    private Long subcomisionId;



}
