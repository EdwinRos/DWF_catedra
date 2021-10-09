package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.SupervisorArea;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.repositories.SupervisorAreaRepository;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Scope(value = "session")
@Component(value = "doctorLogin")
@ELBeanName(value = "doctorLogin")
@Join(path = "/doctor/login", to = "/doctor/login.jsf")
public class DoctorLoginController {

}
