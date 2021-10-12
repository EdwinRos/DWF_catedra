package sv.udb.edu.catedraframeworks.controllers;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sv.udb.edu.catedraframeworks.entities.Administrador;
import sv.udb.edu.catedraframeworks.repositories.AdministradorRepository;
import sv.udb.edu.catedraframeworks.utils.HashSha1;

@Scope(value = "session")
@Component(value = "AdminLogin")
@ELBeanName(value = "AdminLogin")
@Join(path = "/admin/login", to = "/administrador/admin-login.jsf")
public class AdministradorLoginController {

	

	FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
    
    @Autowired
  private AdministradorRepository adminstradoRepository;
    Administrador administrador = new Administrador();
   String passwordAdmin;
    
    public String IniciarSesionDoctor() throws NoSuchAlgorithmException{
    	HashSha1 sha1 = new HashSha1();
    	administrador = adminstradoRepository.findByPasswordAndUsuario(administrador.getUsuario(), sha1.hashPassword(administrador.getPassword()));
   	 if (administrador != null) {
   		 session.setAttribute("id", administrador.getAdministradorId());
   		 session.setAttribute("usuario",administrador.getUsuario());
   		 passwordAdmin = "";
   		 
   		session.setAttribute("id", administrador.getAdministradorId());
   		
   		return "/administrador/ingresardoctor.xhtml?faces-redirect=true";
			
		}else {
			administrador = new Administrador();
			passwordAdmin = "";
			
			return "/administrador/ingresardoctor.xhtml?faces-redirect=true";
		}
    	
    }
    
    public String salirSesion() {
        session.invalidate();
               return "/administrador/admin-login.xhtml?faces-redirect=true";
     }
    
    public Administrador getAdministrador() {
    	return administrador;
	}
    
	
}
