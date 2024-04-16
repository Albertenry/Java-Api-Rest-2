package cadcurriculos.api.domain.candidato;

import cadcurriculos.api.domain.Escolaridade;
import cadcurriculos.api.domain.competencia.Competencia;
import cadcurriculos.api.domain.dto.CandidatoDTO;
import jakarta.persistence.*;
import lombok.*;

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
    private Date dataNascimento;
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
    }
}
