package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Expediente;

public interface ExpedienteRepository extends JpaRepository<Expediente, Integer> {
}
