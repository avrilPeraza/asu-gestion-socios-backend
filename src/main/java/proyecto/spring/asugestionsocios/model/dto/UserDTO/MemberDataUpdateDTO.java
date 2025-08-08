package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "User Member Data model")
public class MemberDataUpdateDTO {

    @NotNull(message = "Profile is mandatory")
    @Schema(example = "2", description = "User's Profile Id")
    private Long profileId;

    @Schema(example = "TRUE", description = "Does User use Sign Language?. Member User Only")
    private Boolean usesSignLanguage = false;

    @Schema(example = "TRUE", description = "Does User have Hearing Impairment?. Member User Only")
    private Boolean hasHearingImpairment = false;

    @Schema(example = "TRUE", description = "Does User belongs To A Committee?. Member User Only")
    private Boolean belongsToCommittee = false;

    @Schema(example = "3", description = "User's Subcommittee Id. Member User Only")
    private Long subcommitteeId = null;
}
