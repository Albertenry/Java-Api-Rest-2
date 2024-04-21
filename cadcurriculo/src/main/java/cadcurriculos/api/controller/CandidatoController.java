package cadcurriculos.api.controller;

import cadcurriculos.api.domain.candidato.CandidatoEntity;
import cadcurriculos.api.domain.candidato.CandidatoRepository;
import cadcurriculos.api.domain.dto.CandidatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("candidatos")
public class CandidatoController {

    private final CandidatoRepository candidatoRepository;

    @Autowired
    public CandidatoController(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CandidatoEntity> cadastrar(@RequestBody CandidatoDTO dados) {
        CandidatoEntity candidatoEntity = new CandidatoEntity(dados);
        candidatoEntity.getCompetencias().forEach(c -> c.setCandidato(candidatoEntity));
        candidatoRepository.save(candidatoEntity);
        return new ResponseEntity<>(candidatoEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CandidatoEntity>> listarTodos(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        Page<CandidatoEntity> candidatos = candidatoRepository.findAll(paginacao);
        return new ResponseEntity<>(candidatos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatoEntity> getCandidatoById(@PathVariable Long id) {
        Optional<CandidatoEntity> optional = candidatoRepository.findById(id);
        return optional.map(candidato -> ResponseEntity.ok().body(candidato))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<CandidatoEntity> updateCandidato(@PathVariable Long id, @RequestBody CandidatoDTO dados) {
        Optional<CandidatoEntity> optional = candidatoRepository.findById(id);
        if (optional.isPresent()) {
            CandidatoEntity candidatoEntity = optional.get();
            candidatoEntity.atualizarDados(dados);
            candidatoRepository.save(candidatoEntity);
            return new ResponseEntity<>(candidatoEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            candidatoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException erro) {
            return ResponseEntity.notFound().build();
        }
    }
}