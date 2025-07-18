package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.PerfilDTO.PerfilDTO;
import proyecto.spring.asugestionsocios.model.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioDTO {
    private Long id;
    private TipoDocumento tipoDocumento;
    private String documento;
    private String email;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String contrasenia;
    private EstadoUsuario estado;
    private Boolean usoLenguajeSenia = false;
    private Boolean difAuditiva = false;
    private String calle;
    private String numPuerta;
    private String apartamento;
    private PerfilDTO perfil;
    private SubcomisionDTO subcomision;
    private List<TelefonoDTO> telefonos = new ArrayList<>();
}
