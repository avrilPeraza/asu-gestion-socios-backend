package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.TipoTelefono;

@Data
public class TelefonoCreateDTO {
    private String numero;
    private TipoTelefono tipoTelefono;
    private Long localidad;

}
