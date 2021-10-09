package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Doctor;

import javax.print.Doc;
import java.util.List;


public interface DoctorRepository extends JpaRepository <Doctor, Integer> {

    //metodos para el area supervisora
    List<Doctor> findByIdAreaAndEstado(Area idArea, Integer estado);

   Doctor findByEstadoAndUsuarioAndPassword(Integer estado, String usuario, String password);

   Doctor findByPasswordAndDoctorId(String password, Integer doctorId);

}
