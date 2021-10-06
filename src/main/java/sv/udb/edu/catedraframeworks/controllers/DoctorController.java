package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sv.udb.edu.catedraframeworks.entities.Administrador;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;

@Scope(value = "session")
@Component(value = "doctorControler")
@ELBeanName(value = "doctorControler")
@Join(path = "/ingresarDoctor", to="/doctor/ingresarDoctor.jsf")
public class DoctorController {
	
	
	  @Autowired
	  private DoctorRepository doctorRepository;
	 Doctor doctor = new Doctor();
	  
	  
	  public String nuevoDoctor(){
		  doctorRepository.save(getDoctor());
	        doctor = new Doctor();
  		    	  
		  return "/registroDoctor.xhtml?faces-redirect=true";
		  
	  }
	  
	  public String delete() {
		  
		  
		  return "/registroDoctor.xhtml?faces-redirect=true";
	  }
	  
	  
	  public Doctor getDoctor() {
		  	 	  
		  return doctor;
	  }

	
}
