package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User Member Data model")
public class MemberDataUpdateDTO {
    private Long profileId;
    private Boolean usesSignLanguage = false;
    private Boolean hasHearingImpairment = false;
    private Boolean belongsToCommittee = false;
    private Long subcommitteeId = null;
}
