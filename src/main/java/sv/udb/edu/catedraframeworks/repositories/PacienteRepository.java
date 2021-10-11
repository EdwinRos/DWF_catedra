package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Paciente findByDuiPaciente(String dui);
}
