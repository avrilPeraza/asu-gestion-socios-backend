package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import lombok.Data;

@Data
public class LocationDTO {
    private Long id;
    private String name;
    private String areaCode;
    private CountryDTO country;
}
