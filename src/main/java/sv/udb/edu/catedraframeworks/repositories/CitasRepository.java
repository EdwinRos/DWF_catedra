package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface CitasRepository extends JpaRepository <Citas, Integer> {

    //metodo utilizados en el area Supervisora y en el área del doctor UwU
    List<Citas> findCitasByIdDoctor(Doctor a);
    List<Citas> findCitasByIdPaciente(Paciente idPaciente);
    List<Citas> findCitasByFechaCitaAfterAndEstadoAndIdPaciente(Date fechaCita, Integer id, Paciente idPaciente);
    List<Citas> findCitasByEstadoAndIdPaciente(Integer estado, Paciente idPaciente);
    List<Citas> findCitasByEstadoAndIdDoctor(Integer estado, Doctor idDoctor);
    Citas findByCodigoCita(String codigoCita);
    Citas findByCitasId(Integer citasId);
    List<Citas> findCitasByFechaCitaAndHoraCitaAndIdDoctor(Date fecha, LocalTime hora, Doctor doctor);

}
