package cadcurriculos.api.controller;

import cadcurriculos.api.domain.admin.AdminRepository;
import cadcurriculos.api.domain.candidato.CandidatoEntity;
import cadcurriculos.api.domain.candidato.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CandidatoRepository candidatoRepository;

    @Autowired
    public AdminController(CandidatoRepository candidatoRepository, AdminRepository adminRepository) {

        this.candidatoRepository = candidatoRepository;
    }

    // Endpoint para visualizar todos os candidatos cadastrados
    @GetMapping("/candidatos")
    public ResponseEntity<List<CandidatoEntity>> getAllCandidatos() {
        List<CandidatoEntity> candidatoEntities = candidatoRepository.findAll();
        return ResponseEntity.ok(candidatoEntities);
    }

    // Endpoint para visualizar detalhes de um candidato específico
    @GetMapping("/candidatos/{id}")
    public ResponseEntity<CandidatoEntity> getCandidatoById(@PathVariable Long id) {
        Optional<CandidatoEntity> optionalCandidato = candidatoRepository.findById(id);
        return optionalCandidato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para aprovar o cadastro de um candidato
    @PutMapping("/candidatos/{id}/aprovar")
    public ResponseEntity<String> aprovarCadastro(@PathVariable Long id) {
        Optional<CandidatoEntity> optionalCandidato = candidatoRepository.findById(id);
        if (optionalCandidato.isPresent()) {
            CandidatoEntity candidatoEntity = optionalCandidato.get();
            candidatoEntity.setStatus("Aprovado");
            candidatoRepository.save(candidatoEntity);
            return ResponseEntity.ok("Cadastro do candidato aprovado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para reprovar o cadastro de um candidato
    @PutMapping("/candidatos/{id}/reprovar")
    public ResponseEntity<String> reprovarCadastro(@PathVariable Long id) {
        Optional<CandidatoEntity> optionalCandidato = candidatoRepository.findById(id);
        if (optionalCandidato.isPresent()) {
            CandidatoEntity candidatoEntity = optionalCandidato.get();
            candidatoEntity.setStatus("Reprovado");
            candidatoRepository.save(candidatoEntity);
            return ResponseEntity.ok("Cadastro do candidato reprovado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
