package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import lombok.Data;

@Data
public class LocalidadDTO {
    private Long id;
    private String nombre;
    private String codigoArea;
    private PaisDTO pais;
}
