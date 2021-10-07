package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;

@Scope(value = "session")
@Component(value = "doctoPerfilrController")
@ELBeanName(value = "doctorPerfilController")
@Join(path = "/doctor/perfil", to="/doctor/perfil-doctor.jsf")
public class DoctorPerfilController {
	
}
