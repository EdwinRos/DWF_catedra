package sv.udb.edu.catedraframeworks.controllers;

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
import java.util.Optional;


@Scope(value = "session")
@Component(value = "doctorLogin")
@ELBeanName(value = "doctorLogin")
@Join(path = "/doctor/login", to = "/doctor/login.jsf")
public class DoctorLoginController {
	FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    
    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();
    String password;
    
    public String login() throws NoSuchAlgorithmException {
    	HashSha1 sha1 = new HashSha1();
    	
    	doctor = doctorRepository.findByEstadoAndUsuarioAndPassword(1, doctor.getUsuario(), sha1.hashPassword(doctor.getPassword()));
    	
    	if (doctor != null) {
    		session.setAttribute("doctorId", doctor.getDoctorId());
    		session.setAttribute("areaId", doctor.getIdArea().getAreaId());
    		
    		doctor = null;
    		password = null;
    		
    		return "/doctor/dashboard.xhtml?faces-redirect=true";
    	} else {
    		doctor = null;
    		
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion","Usuario o contraseña incorrectos"));
            
    		return "/doctor/login.xhtml?faces-redirect=true";
    	}
    }
    
    public String logout() {
        session.invalidate();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Logout","Se ha cerrado sesion"));
        
        return "/doctor/login.xhtml?faces-redirect=true";
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
