package sv.udb.edu.catedraframeworks.controllers;

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
    public void loadData() {
    	ActualAge edadActual = new ActualAge();
    	
    	 Optional<Doctor> miDoctor = doctorRepository.findById(1);
    	 
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
    	
    	citasDoctor();
    }
    
    protected void citasDoctor() {
    	citas = citasRepository.findAll(); 
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
