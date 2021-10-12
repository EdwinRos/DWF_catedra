package sv.udb.edu.catedraframeworks.controllers.supervisor;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.HashSha1;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;


@Scope(value = "session")
@Component(value = "administradorInicio")
@ELBeanName(value = "administradorInicio")
@Join(path = "/supervisor.do", to = "/supervisor_area/supervisorlogin.jsf")
public class SupervisorLoginRouting {

    //setiamos lo que nesecitamos para poder utilizar httpSession
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    @Autowired
    private DoctorRepository doctorRepository;
    Doctor loginDoctor = new Doctor();
    String password;

    public String iniciarSession() throws NoSuchAlgorithmException {
        HashSha1 sha1 = new HashSha1();
        loginDoctor = doctorRepository.findByEstadoAndUsuarioAndPassword(3, loginDoctor.getUsuario(), sha1.hashPassword(getPassword()));
        if(loginDoctor != null) {

            session.setAttribute("id", loginDoctor.getDoctorId()); //session.get("identificador", "variable")
            session.setAttribute("nombre", loginDoctor.getNombreDoctor());
            session.setAttribute("apellido", loginDoctor.getApellidoDoctor());
            loginDoctor = new Doctor();
            password = "";
            return "/supervisor_area/inicio.xhtml?faces-redirect=true";
        }else{
            loginDoctor = new Doctor();
            password = "";
            setloginNullMessage();
            return null;
        }
    }


    public String logOut() {
        session.invalidate();
        setLogoutMessage();
        return "/supervisor_area/supervisorlogin.xhtml?faces-redirect=true";
    }

    //mensajes en area login
    public void setloginNullMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion","Usuario o contraseña incorrectos"));
    }

    public void setLogoutMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion","Usuario o contraseña incorrectos"));
    }

    public Doctor getLoginDoctor() {
        return loginDoctor;
    }

    public String getPassword() {
        return password;
    }

    public void setLoginDoctor(Doctor loginDoctor) {
        this.loginDoctor = loginDoctor;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
