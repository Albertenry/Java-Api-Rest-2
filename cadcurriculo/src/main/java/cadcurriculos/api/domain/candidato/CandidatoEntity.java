package cadcurriculos.api.domain.candidato;

import cadcurriculos.api.domain.Escolaridade;
import cadcurriculos.api.domain.competencia.Competencia;
import cadcurriculos.api.domain.dto.CandidatoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Candidato")
@Table(name = "candidatos")
public class CandidatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private @NotNull Date dataNascimento;

    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;

    private String funcao;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competencia> competencias;

    public CandidatoEntity(CandidatoDTO dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.escolaridade = dados.escolaridade();
        this.funcao = dados.funcao();
        this.competencias = dados.competencias().stream()
                .map(Competencia::new)
                .collect(Collectors.toList());
    }

    public void setStatus(String reprovado) {
    }

    public void atualizarDados(CandidatoDTO dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.escolaridade = dados.escolaridade();
        this.funcao = dados.funcao();

        // Limpa as competências existentes
        this.competencias.clear();

        // Adiciona as novas competências
        if (dados.competencias() != null) {
            dados.competencias().forEach(compDTO -> {
                Competencia competencia = new Competencia(compDTO);
                competencia.setCandidato(this); // Define o candidato para a competência
                this.competencias.add(competencia);
            });
        }
    }
}