package sv.udb.edu.catedraframeworks.controllers.supervisor;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.SupervisorArea;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.repositories.SupervisorAreaRepository;
import sv.udb.edu.catedraframeworks.utils.HashSha1;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@Scope(value = "session")
@Component(value = "administradorInicio")
@ELBeanName(value = "administradorInicio")
@Join(path = "/supervisor.do", to = "/supervisor_area/supervisorlogin.jsf")
public class SupervisorLoginRouting {

    //setiamos lo que nesecitamos para poder utilizar httpSession
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);


    @Autowired
    private SupervisorAreaRepository supervisorAreaRepository;
    SupervisorArea supervisorArea = new SupervisorArea();


    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();
    String password;

    public String iniciarSession() throws NoSuchAlgorithmException {
        HashSha1 sha1 = new HashSha1();


        doctor = doctorRepository.findByEstadoAndUsuarioAndPassword(3, doctor.getUsuario(), sha1.hashPassword(getPassword()));
        if(doctor != null) {

            session.setAttribute("id", doctor.getDoctorId()); //session.get("identificador", "variable")
            session.setAttribute("nombre", doctor.getNombreDoctor());
            session.setAttribute("apellido", doctor.getApellidoDoctor());
            doctor = null;
            password = null;
            return "/supervisor_area/inicio.xhtml?faces-redirect=true";
        }else{
            doctor = null;
            password = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion","Usuario o contraseña incorrectos"));
            return "/supervisor_area/supervisorlogin.xhtml?faces-redirect=true";
        }

    }

    public String logOut() {
        session.invalidate();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Logout","Se ha cerrado sesion"));
        return "/supervisor_area/supervisorlogin.xhtml?faces-redirect=true";
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}