package ni.edu.uam.reservas_api.repository;

import ni.edu.uam.reservas_api.model.SalaEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaEstudioRepository extends JpaRepository<SalaEstudio, Long> {
}