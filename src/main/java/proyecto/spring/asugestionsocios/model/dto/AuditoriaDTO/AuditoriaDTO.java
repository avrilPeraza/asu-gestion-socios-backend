package proyecto.spring.asugestionsocios.model.dto.AuditoriaDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.FuncionalidadDTO.FuncionalidadDTO;
import proyecto.spring.asugestionsocios.model.dto.UsuarioDTO.UsuarioDTO;

import java.time.LocalDateTime;
@Data
public class AuditoriaDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String terminal;
    private String descripcion;
    private FuncionalidadDTO funcionalidad;
    private UsuarioDTO usuario;
}
