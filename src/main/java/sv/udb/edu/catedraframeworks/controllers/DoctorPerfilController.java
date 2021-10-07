package sv.udb.edu.catedraframeworks.controllers;

import java.util.Optional;

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
import sv.udb.edu.catedraframeworks.utils.ActualAge;

@Scope(value = "session")
@Component(value = "doctorPerfilController")
@ELBeanName(value = "doctorPerfilController")
@Join(path = "/doctor/perfil", to="/doctor/perfil-doctor.jsf")
public class DoctorPerfilController {
	@Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();
    
    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {    	
    	 Optional<Doctor> miDoctor = doctorRepository.findById(1);
    	 
    	 doctor.setDoctorId(miDoctor.get().getDoctorId());
    	 doctor.setUsuario(miDoctor.get().getUsuario());
         doctor.setNombreDoctor(miDoctor.get().getNombreDoctor());
         doctor.setApellidoDoctor(miDoctor.get().getApellidoDoctor());
         doctor.setIdArea(miDoctor.get().getIdArea());
         doctor.setEstado(miDoctor.get().getEstado());
         doctor.setFechaNacimiento(miDoctor.get().getFechaNacimiento());
         doctor.setCorreoDoctor(miDoctor.get().getCorreoDoctor());
         doctor.setDuiDoctor(miDoctor.get().getDuiDoctor());
         doctor.setFechaRegistro(miDoctor.get().getFechaRegistro());
         doctor.setIdArea(miDoctor.get().getIdArea());
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
}
