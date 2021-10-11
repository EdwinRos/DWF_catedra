package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "RecepcionistaConsultarPerfilPaciente")
@ELBeanName(value = "RecepcionistaConsultarPerfilPaciente")
@Join(path = "/consultarperfilpaciente", to="/recepcionista/consultar-perfil-paciente.jsf")
public class RecepcionistaConsultarPerfilPaciente {

}
