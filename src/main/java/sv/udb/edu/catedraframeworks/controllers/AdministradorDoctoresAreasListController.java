package sv.udb.edu.catedraframeworks.controllers;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sv.udb.edu.catedraframeworks.entities.DoctoresArea;
import sv.udb.edu.catedraframeworks.repositories.DoctoresAreaRepository;

@Scope(value = "session")
@Component(value = "doctorAreaList")
@ELBeanName(value = "doctorAreaList")
@Join(path = "/admin/listareadoctor", to = "/administrador/administrador-listado-doctores.jsf")
public class AdministradorDoctoresAreasListController {

	@Autowired
	private DoctoresAreaRepository doctoresAreaRepository;
	private List<DoctoresArea> doctoresAreas;
	
	
	 	@Deferred
	    @RequestAction
	    @IgnorePostback
	    public void cargarListDoctorArea() {
	 		doctoresAreas = doctoresAreaRepository.findAll();
	 	}
	 	
	 	public List<DoctoresArea> getDoctoresAreas(){
	 		return doctoresAreas;
	 	}
	
	
	
}
