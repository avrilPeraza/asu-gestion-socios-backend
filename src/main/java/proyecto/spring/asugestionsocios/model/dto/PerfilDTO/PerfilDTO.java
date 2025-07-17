package proyecto.spring.asugestionsocios.model.dto.PerfilDTO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import proyecto.spring.asugestionsocios.model.entity.Estado;
import proyecto.spring.asugestionsocios.model.entity.Funcionalidad;
import proyecto.spring.asugestionsocios.model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

@Data
public class PerfilDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Estado estado;
}
