package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.udb.edu.catedraframeworks.entities.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Paciente findByDuiPaciente(String dui);
    Paciente findByCorreoPaciente(String correo);
    Paciente findByTelefono(String telefono);
    Paciente findByUsuario(String dui);


    Paciente findByCorreoPacienteAndPassword(String correoPaciente, String password);

}
