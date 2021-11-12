package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Expediente;
import sv.udb.edu.catedraframeworks.entities.Paciente;

import java.util.List;

public interface ExpedienteRepository extends JpaRepository<Expediente, Integer> {

    //obtener Listado de records(exoediente) para el supervisor
    List<Expediente> findExpedienteByPacienteId(Paciente p);
    List<Expediente> findExpedienteByPacienteIdOrderByFechaRegistroDesc(Paciente p);

}
