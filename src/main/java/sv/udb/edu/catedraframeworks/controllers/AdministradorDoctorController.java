package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sv.udb.edu.catedraframeworks.entities.Administrador;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.HashSha1;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Scope(value = "session")
@Component(value = "AdminDoctorControler")
@ELBeanName(value = "AdminDoctorControler")
@Join(path = "/administradoringresardoctor", to="/administrador/ingresardoctor.jsf")
public class AdministradorDoctorController {

	  @Autowired
	  private DoctorRepository doctorRepository;
	  Doctor doctor = new Doctor();

	  @Autowired
	  private AreaRepository areaRepository;
	  private List<Area> areas;

	  Area area = new Area();




	  public String nuevoDoctor() throws NoSuchAlgorithmException {
	  	//String contraRamdom = metodo que te va a regresar el string al azar



	  	HashSha1 hasSha1 = new HashSha1();



	  	 doctor.setEstado(1);
	  	 doctor.setDuiDoctor("11002548-5");
	  	 doctor.setCorreoDoctor("prueba4.mail.com");
	  	 doctor.setUsuario("prueba3");
	  	 doctor.setFechaRegistro(new Date());
	  	 doctor.setFechaNacimiento(new Date(1975,11,29));
	  	 doctor.setIdArea(area);
	  	 doctor.setPassword(hasSha1.hashPassword("12345"));
		  doctorRepository.save(doctor);
		  //metodo para enviar email
		  doctor = new Doctor();
  		    	  
		  return "/ingresardoctor.xhtml?faces-redirect=true";
		  
	  }

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadAreas(){
	  	areas = areaRepository.findAll();
	}


	  
	  public String delete() {
		  
		  
		  return "/registroDoctor.xhtml?faces-redirect=true";
	  }
	  
	  
	  public Doctor getDoctor() {
		  return doctor;
	  }

	public List<Area> getAreas() {
		return areas;
	}

	public Area getArea() {
		return area;
	}
}
