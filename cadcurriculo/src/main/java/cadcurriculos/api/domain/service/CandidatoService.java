package cadcurriculos.api.domain.service;

import cadcurriculos.api.domain.candidato.CandidatoEntity;
import cadcurriculos.api.domain.candidato.CandidatoRepository;
import cadcurriculos.api.domain.dto.CandidatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

    @Autowired
    public CandidatoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    @Transactional
    public CandidatoEntity cadastrar(CandidatoDTO dados) {
        CandidatoEntity candidatoEntity = new CandidatoEntity(dados);
        candidatoEntity.getCompetencias().forEach(c -> c.setCandidato(candidatoEntity));
        return candidatoRepository.save(candidatoEntity);
    }

    public Optional<CandidatoEntity> buscarPorId(Long id) {
        return candidatoRepository.findById(id);
    }


}
