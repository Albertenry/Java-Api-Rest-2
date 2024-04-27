package cadcurriculos.api.domain.competencia;

import cadcurriculos.api.domain.candidato.CandidatoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cadcurriculos.api.domain.dto.CompetenciaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Competencia")
@Table(name = "competencias")
public class Competencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int nivelProficiencia;

    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    @JsonIgnore // Adicionando esta anotação para ignorar a serialização recursiva
    private CandidatoEntity candidato;

    public Competencia(CompetenciaDTO competenciaDTO) {
        this.descricao = competenciaDTO.descricao();
        this.nivelProficiencia = competenciaDTO.nivelProficiencia();
    }

    public Competencia(Object o) {
    }
}
