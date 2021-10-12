package sv.udb.edu.catedraframeworks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.udb.edu.catedraframeworks.entities.Administrador;
import sv.udb.edu.catedraframeworks.entities.Doctor;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
	
	
	  Administrador findByPasswordAndUsuario(String password, String usuario);
}
