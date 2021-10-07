package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import java.util.List;

public interface CitasRepository extends JpaRepository <Citas, Integer> {

    //metodo utilizados en el area Supervisora y en el área del doctor UwU
    List<Citas> findCitasByIdDoctor(Doctor a);

}
