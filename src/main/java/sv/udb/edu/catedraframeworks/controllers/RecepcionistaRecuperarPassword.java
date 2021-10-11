package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "RecepcionistaRecuperarPassword")
@ELBeanName(value = "RecepcionistaRecuperarPassword")
@Join(path = "/recuperarpasswordpaciente", to="/recepcionista/recuperar-password-paciente.jsf")
public class RecepcionistaRecuperarPassword {

}
