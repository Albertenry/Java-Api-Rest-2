package cadcurriculos.api.domain.dto;

import cadcurriculos.api.domain.Escolaridade;
import cadcurriculos.api.domain.dto.CompetenciaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public record CandidatoDTO(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        Date dataNascimento,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9,11}")
        String telefone,
        @NotNull
        @Valid
        Escolaridade escolaridade,
        @NotBlank
        String funcao,
        @NotNull
        @Valid
        List<CompetenciaDTO> competencias
) {
        public CandidatoDTO {
                if (competencias == null) {
                        competencias = Collections.emptyList();
                }
        }

        public List<CompetenciaDTO> competencias() {
                return competencias;
        }
}