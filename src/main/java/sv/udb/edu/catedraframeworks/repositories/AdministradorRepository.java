package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
