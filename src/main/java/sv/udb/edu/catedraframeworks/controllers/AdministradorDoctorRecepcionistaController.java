package sv.udb.edu.catedraframeworks.controllers;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Recepcionista;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.RecepcionistaRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "AdminRepecionistaControler")
@ELBeanName(value = "AdminRepecionistaControler")
@Join(path = "/admin/ingresarrecepcionista", to = "/administrador/administrador-ingresar-recepcionista.jsf")
public class AdministradorDoctorRecepcionistaController {

	
	@Autowired
	private RecepcionistaRepository recepcionistaRepository;
	Recepcionista recepcionista = new Recepcionista();
	
	
	
	@Autowired
	private AreaRepository areaRepository;
	  private List<Area> areasrec;
	  
	  Area area = new Area();
	
	
	public String saveRecepcionista() {
		recepcionista.setAreaId(area);
		recepcionistaRepository.save(recepcionista);
		recepcionista = new Recepcionista();
		
		
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito","Recepcionista Ingresado Correctamente"));
		
		recepcionista = null;
		
		/*
		recepcionista.setNombreRecepcionista("");
		recepcionista.setApellidoRecepcionista("");
		recepcionista.setUsuario("");
		recepcionista.setPassword("");
		*/
		return "/administrador-ingresar-recepcionista.xhtml?faces-redirect=true";
		
	}
	
	
	
	
	  	@Deferred
	    @RequestAction
	    @IgnorePostback
	    public void loadAreas() {
	        areasrec = areaRepository.findAll();
	    }
	  	
	  	

	  	public String deleteRecepcionista() {
	  		int id = Integer.valueOf(JsfUtil.getRequest().getParameter("Idrecepcionista"));
			recepcionistaRepository.deleteById(id);
			recepcionista = new Recepcionista();
			 
			
			return "/administrador/administrador-recepcionista-lista.xhtml?faces-redirect=true";
		}
	

	  	
	

		public Recepcionista getRecepcionista() {
			return recepcionista;
		}
		
		public List<Area> getAreas() {
			return areasrec;
		}
		
		public Area getArea() {
		return area;
		}
}
