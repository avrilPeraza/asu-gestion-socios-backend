package proyecto.spring.asugestionsocios.model.dto.FuncionalidadDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Acesso;

@Data
public class AccesoFuncionalidadDTO {
    private Long perfilId;
    private Long funcionalidadId;
    private Acesso acesso;
}
