package sv.udb.edu.catedraframeworks.repositories;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.SupervisorArea;

public interface SupervisorAreaRepository extends JpaRepository<SupervisorArea, Integer> {

    SupervisorArea findByIdDoctor(Doctor a);

}
