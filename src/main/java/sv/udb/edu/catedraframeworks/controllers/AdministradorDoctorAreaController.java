package sv.udb.edu.catedraframeworks.controllers;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "AdminAreaDoctorControler")
@ELBeanName(value = "AdminAreaDoctorControler")
@Join(path = "/admindoctorarea", to = "/administrador/administrador-doctor-area-nuevo.jsf")
public class AdministradorDoctorAreaController {

	

	
	
	
}
