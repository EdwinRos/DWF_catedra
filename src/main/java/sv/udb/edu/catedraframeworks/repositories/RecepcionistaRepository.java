package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Recepcionista;

public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Integer> {
    Recepcionista findByUsuarioAndPassword(String usuario, String password);
}
