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

import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Recepcionista;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.RecepcionistaRepository;

@Scope(value = "session")
@Component(value = "AdminrecepcionistaList")
@ELBeanName(value = "AdminrecepcionistaList")
@Join(path = "/recepcionistalista", to = "/administrador/administrador-recepcionista-lista.jsf")

public class AdministradorDoctorRecepcionistaListController {

	@Autowired
	private RecepcionistaRepository recepcionistaRepository;
	private List<Recepcionista> recepcionistalist;
	
	
	@Autowired
	private AreaRepository areaRepository;
	Area area = new Area();
	
		@Deferred
	    @RequestAction
	    @IgnorePostback
	    public void cargarListRecepcionista() {
			recepcionistalist = recepcionistaRepository.findAll();
		}
		
		public List<Recepcionista> getRecepcionistalist() {
			return recepcionistalist;
		}
}
