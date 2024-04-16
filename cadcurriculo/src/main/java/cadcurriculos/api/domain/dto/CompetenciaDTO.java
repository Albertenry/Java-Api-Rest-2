package cadcurriculos.api.domain.dto;

import jakarta.validation.constraints.NotNull;


public record CompetenciaDTO(

        @NotNull
        String descricao,

        int nivelProficiencia

) {

}
