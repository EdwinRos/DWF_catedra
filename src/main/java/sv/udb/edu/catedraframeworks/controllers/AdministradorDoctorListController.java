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


import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;

@Scope(value = "session")
@Component(value = "doctorList")
@ELBeanName(value = "doctorList")
@Join(path = "/listadoDoctor", to = "/listDoctor.jsf")
public class AdministradorDoctorListController {
	
	 @Autowired
	    private DoctorRepository doctorRepository;
	    private List<Doctor> doctors;

	    @Deferred
	    @RequestAction
	    @IgnorePostback
	    public void cargarList(){
	        doctors = doctorRepository.findAll();
	    }

	    public List<Doctor> getDoctors() {
	        return doctors;
	    }
	

}
