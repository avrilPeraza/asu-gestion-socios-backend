package proyecto.spring.asugestionsocios.model.dto.FuncionalidadDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Estado;

@Data
public class FuncionalidadDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Estado estado;
}
