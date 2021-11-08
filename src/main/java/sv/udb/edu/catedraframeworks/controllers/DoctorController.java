package sv.udb.edu.catedraframeworks.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.context.FacesContext;
import javax.mail.Session;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.ActualAge;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "doctorController")
@ELBeanName(value = "doctorController")
@Join(path = "/doctor/home", to="/doctor/dashboard.jsf")
public class DoctorController {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	@Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();
	
	int edad = 0;
	
	@Autowired
    private CitasRepository citasRepository;
    private List<Citas> citas;
	
    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() throws ParseException {
    	ActualAge edadActual = new ActualAge();
    	
    	int idDoctor = Integer.parseInt(String.valueOf(session.getAttribute("doctorId")));
    	
    	Optional<Doctor> miDoctor = doctorRepository.findById(idDoctor);
    	 
		doctor.setDoctorId(miDoctor.get().getDoctorId());
		doctor.setUsuario(miDoctor.get().getUsuario());
		doctor.setNombreDoctor(miDoctor.get().getNombreDoctor());
		doctor.setApellidoDoctor(miDoctor.get().getApellidoDoctor());
		doctor.setIdArea(miDoctor.get().getIdArea());
		doctor.setEstado(miDoctor.get().getEstado());
		doctor.setCorreoDoctor(miDoctor.get().getCorreoDoctor());
		doctor.setDuiDoctor(miDoctor.get().getDuiDoctor());
		doctor.setFechaRegistro(miDoctor.get().getFechaRegistro());
		doctor.setIdArea(miDoctor.get().getIdArea());        
		
		edad = edadActual.getActualDate(miDoctor.get().getFechaNacimiento());
		
    	citasDoctor(doctor);
    }
    
    protected void citasDoctor(Doctor d) throws ParseException {  
    	List<Citas> citasBusqueda = citasRepository.findCitasByIdDoctor(d);
    	
    	for (Citas cita : citasBusqueda) {
    		Citas citaActual = cita;    		
    		
    		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    		
    		Date citaFecha = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(citaActual.getFechaCita()));
    		Date fechaActual = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);

    		if (citaFecha.before(fechaActual)) {
    			citaActual.setEstado(3);
    			
    			citasRepository.save(citaActual);
    		}
    		
    		citaActual = null;
    	}
    	
    	citas = citasRepository.findCitasByIdDoctor(d);
    }
	
    public Doctor getDoctor() {
        return doctor;
    }

    public int getEdad() {
        return edad;
    }

    public List<Citas> getCitas() {
        return citas;
    }
}
