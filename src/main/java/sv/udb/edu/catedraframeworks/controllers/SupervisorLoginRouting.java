package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "administradorInicio")
@ELBeanName(value = "administradorInicio")
@Join(path = "/supervisor.do", to = "/supervisor_area/supervisorlogin.jsf")
public class SupervisorLoginRouting {

    //solo es para enrutar :v


}
