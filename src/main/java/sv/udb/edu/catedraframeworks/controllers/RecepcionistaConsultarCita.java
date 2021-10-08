package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "RecepcionistaConsultarCitaController")
@ELBeanName(value = "RecepcionistaConsultarCitaController")
@Join(path = "/consultarcita", to="/recepcionista/consultar-cita-paciente.jsf")
public class RecepcionistaConsultarCita {

}
