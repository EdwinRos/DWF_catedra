package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "RecepcionistaRecuperarContraseña")
@ELBeanName(value = "RecepcionistaRecuperarContraseña")
@Join(path = "/recuperarcontraseñapaciente", to="/recepcionista/recuperar-contraseña-paciente.jsf")
public class RecepcionistaRecuperarContraseña {

}
