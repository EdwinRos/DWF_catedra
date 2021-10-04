package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Citas;

public interface CitasRepository extends JpaRepository <Citas, Integer> {
}
