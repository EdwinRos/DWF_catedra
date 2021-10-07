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
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.DoctoresArea;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctoresAreaRepository;

@Scope(value = "session")
@Component(value = "AdminAreaDoctorControler")
@ELBeanName(value = "AdminAreaDoctorControler")
@Join(path = "/administradordoctorarea", to = "/administrador/administrador-doctor-area-nuevo.jsf")
public class AdministradorDoctoresAreaController {

	   @Autowired
	   private DoctoresAreaRepository doctoresAreaRepository;
	   DoctoresArea doctoresArea = new DoctoresArea();
	   
	   @Autowired
	   private DoctorRepository doctorRepository;
	   Doctor doctor = new Doctor();
	   private List<Doctor> doctores;
	   
	   @Autowired 
	   private AreaRepository areaRepository;
	   Area area = new Area();
	
	   
	   
	   public String saveAreaDoctor() {
		   doctoresAreaRepository.save(getDoctoresArea());
		   doctoresArea = new DoctoresArea();
		   
		   return "/administrador/administrador-doctor-area-nuevo.xhtml?faces-redirect=true";
	   }
	   
	   
	   	@Deferred
	    @RequestAction
	    @IgnorePostback
	    public void loadSupervisiores() {
	   		doctores = doctorRepository.findAll();
	   	}
	   
	   
	   public DoctoresArea getDoctoresArea() {
		return doctoresArea;
	}
	   public List<Doctor> getDoctores() {
		return doctores;
	}
	   public Doctor getDoctor() {
		return doctor;
	}
	   
	  
	   
}
