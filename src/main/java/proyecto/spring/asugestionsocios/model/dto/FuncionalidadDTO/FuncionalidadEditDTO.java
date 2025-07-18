package proyecto.spring.asugestionsocios.model.dto.FuncionalidadDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Estado;

@Data
public class FuncionalidadEditDTO {
    private Long id;
    private String descripcion;
    private Estado estado;
}
