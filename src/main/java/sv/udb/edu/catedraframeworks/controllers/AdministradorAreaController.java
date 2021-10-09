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
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;

@Scope(value = "session")
@Component(value = "AdminAreaControler")
@ELBeanName(value = "AdminAreaControler")
@Join(path = "/administradorareaingresar", to="/administrador/administrador-area-ingresararea.jsf")
public class AdministradorAreaController {
	
	
	@Autowired
	private AreaRepository areaRepository;
	Area area = new Area();
	private List<Area>arealistas;
	


	    @Deferred
	    @RequestAction
	    @IgnorePostback
	    public void areaList(){
	       areaRepository.findAll(); 
	    }

	
	public String nuevaArea() {
		areaRepository.save(area);
		area = new Area();
		
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito","Area Ingresada Correctamente"));
		
		area.setNombreArea("");
		
		
		return "/administrador-area-ingresararea.xhtml?faces-redirect=true";
	}
	
	
	
	public String deleteArea() {
		areaRepository.delete(area);
		area = new Area();
		
		
		return "/administrador-area-ingresararea.xhtml?faces-redirect=true";
		
	}
	
public Area getArea() {
	return area;
}

public List<Area> getArealistas() {
	return arealistas;
}

}
