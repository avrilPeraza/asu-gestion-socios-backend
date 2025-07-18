package proyecto.spring.asugestionsocios.model.dto.EspacioDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Estado;

import java.math.BigDecimal;
import java.time.LocalDate;

/*Espacio no requiere más DTOs ya que se usan los mismos atributos,
en casos como editar se le agrega otros atributos que quedan como opcional
para otras acciones*/
@Data
public class EspacioDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer capacidad;
    private BigDecimal tarifaNoSocio;
    private BigDecimal tarifaSocio;
    private LocalDate fechaVigenciaPrecio;
    private String observaciones;
    private Estado estado;
}
