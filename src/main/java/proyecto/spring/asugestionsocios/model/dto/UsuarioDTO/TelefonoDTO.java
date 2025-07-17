package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.TipoTelefono;

@Data
public class TelefonoDTO {
    private Long id;
    private String numero;
    private TipoTelefono tipoTelefono;
    private LocalidadDTO localidadId;

}
