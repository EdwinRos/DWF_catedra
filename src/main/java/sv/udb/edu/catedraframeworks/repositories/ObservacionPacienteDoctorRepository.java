package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.ObservacionPacienteDoctor;

import java.util.List;

public interface ObservacionPacienteDoctorRepository extends JpaRepository<ObservacionPacienteDoctor, Integer> {

    List<ObservacionPacienteDoctor> getObservacionPacienteDoctorByIdDoctor(Doctor id);
    List<ObservacionPacienteDoctor> findObservacionPacienteDoctorByIdDoctorOrderByFechaRegistroDesc(Doctor doctor);
}
