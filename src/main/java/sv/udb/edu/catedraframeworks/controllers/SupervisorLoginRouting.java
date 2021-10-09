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
@Component(value = "administradorInicio")
@ELBeanName(value = "administradorInicio")
@Join(path = "/supervisor.do", to = "/supervisor_area/supervisorlogin.jsf")
public class SupervisorLoginRouting {

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);


    @Autowired
    private SupervisorAreaRepository supervisorAreaRepository;
    SupervisorArea supervisorArea = new SupervisorArea();


    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();

    public String iniciarSession() {
        doctor = doctorRepository.findByEstadoAndUsuarioAndPassword(3, "prueba", "hola1234");
        session.setAttribute("id",doctor.getDoctorId());
        return "/supervisor_area/inicio.xhtml?faces-redirect=true";
    }

    public String logOut() {
        session.invalidate();

        return "/supervisor_area/supervisorlogin.xhtml?faces-redirect=true";
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
