package sv.udb.edu.catedraframeworks.controllers;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

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
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "AdminAreaDoctorControler")
@ELBeanName(value = "AdminAreaDoctorControler")
@Join(path = "/admin/areasupervisor", to = "/administrador/administrador-doctor-area-nuevo.jsf")
public class AdministradorDoctorAreaController {

	@Autowired
	private AreaRepository areaRepository;
	Area area = new Area();
	private List<Area> arealist;

	@Autowired
	private DoctorRepository doctorRepository;
	Doctor doctor = new Doctor();
	private List<Doctor> doctorlist;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void asignarSupervisor() {
		doctor = doctorRepository.getById(Integer.valueOf(JsfUtil.getRequest().getParameter("idDoctor")));
		doctor.setEstado(3);
		
		
		
	}
	
	
	public void nuevoSupervisor() {
		doctorRepository.save(doctor);
		
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public Area getArea() {
		return area;
	}

	public List<Area> getAreas() {
		return arealist;
	}

}
