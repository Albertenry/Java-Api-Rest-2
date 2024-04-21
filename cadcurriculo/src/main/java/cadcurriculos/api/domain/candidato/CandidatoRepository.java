package cadcurriculos.api.domain.candidato;

import cadcurriculos.api.domain.candidato.CandidatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<CandidatoEntity, Long> {

}

