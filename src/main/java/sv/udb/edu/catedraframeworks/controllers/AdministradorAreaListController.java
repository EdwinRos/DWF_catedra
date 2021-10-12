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
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;

@Scope(value = "session")
@Component(value = "AdminListArea")
@ELBeanName(value = "AdminListArea")
@Join(path = "/admin/listarea", to="/administrador/administrador-area-lista.jsf")
public class AdministradorAreaListController {
	
	
	@Autowired
	private AreaRepository areaRepository;
	private List<Area> areaList;
	
	
	@Deferred
	@RequestAction
	@IgnorePostback
	public void cargarListArea() {
		areaList = areaRepository.findAll();
	}
	
	public List<Area> getAreaList() {
		return areaList;
	}
}
