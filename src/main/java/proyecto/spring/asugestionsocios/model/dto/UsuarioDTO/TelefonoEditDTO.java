package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import proyecto.spring.asugestionsocios.model.entity.TipoTelefono;

public class TelefonoEditDTO {
    private Long id;
    private String numero;
    private TipoTelefono tipoTelefono;
    private LocalidadDTO localidadId;
}
