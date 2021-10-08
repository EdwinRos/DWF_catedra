package sv.udb.edu.catedraframeworks.controllers;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "AdminAreaDoctorControler")
@ELBeanName(value = "AdminAreaDoctorControler")
@Join(path = "/admindoctorarea", to = "/administrador/administrador-doctor-area-nuevo.jsf")
public class AdministradorDoctorAreaController {

	/*
	   @Autowired
	   private DoctoresAreaRepository doctoresAreaRepository;
	   DoctoresArea doctoresArea = new DoctoresArea();
	   private List<Area> areas;
	   
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
	   
	 */ 

	
	
	
}
