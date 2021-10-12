package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.Recepcionista;
import sv.udb.edu.catedraframeworks.entities.SupervisorArea;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.repositories.RecepcionistaRepository;
import sv.udb.edu.catedraframeworks.repositories.SupervisorAreaRepository;
import sv.udb.edu.catedraframeworks.utils.HashSha1;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "RecepcionistaLogin")
@ELBeanName(value = "RecepcionistaLogin")
@Join(path = "/recepcionista/login", to = "/recepcionista/login-recepcionista.jsf")
public class RecepcionistaLogin {

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    Recepcionista recepcionista = new Recepcionista();
    String password;

    public String iniciarSesionRecepcionista() throws NoSuchAlgorithmException {
        HashSha1 sha1 = new HashSha1();

        recepcionista = recepcionistaRepository.findByUsuarioAndPassword(recepcionista.getUsuario(), sha1.hashPassword(recepcionista.getPassword()));

        if(recepcionista != null) {
            session.setAttribute("id", recepcionista.getRecepcionistaId());
            return "/recepcionista/inicio.xhtml?faces-redirect=true";
        }else{
            recepcionista = new Recepcionista();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion","Usuario o contraseña incorrectos"));
            return "/recepcionista/login-recepcionista.xhtml?faces-redirect=true";
        }
    }

    public String logOut() {
        session.invalidate();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Logout","Se ha cerrado sesion"));
        return "/recepcionista/login-recepcionista.xhtml?faces-redirect=true";
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }
}
