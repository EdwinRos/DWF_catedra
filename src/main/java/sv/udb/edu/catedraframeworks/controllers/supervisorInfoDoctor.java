package sv.udb.edu.catedraframeworks.controllers;

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

import java.util.Optional;

@Scope(value = "session")
@Component(value = "doctorInfo")
@ELBeanName(value = "doctorInfo")
@Join(path = "/informacion", to = "/supervisor_area/doctorinfo.jsf")
public class supervisorInfoDoctor {
    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();
    int edad = 0;
    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadDoctor(){

        ActualAge actualAge = new ActualAge();

        Optional<Doctor> miDoctor = doctorRepository.findById(3);
        doctor.setDoctorId(miDoctor.get().getDoctorId());
        doctor.setNombreDoctor(miDoctor.get().getNombreDoctor());
        doctor.setApellidoDoctor(miDoctor.get().getApellidoDoctor());
        doctor.setIdArea(miDoctor.get().getIdArea());
        doctor.setCorreoDoctor(miDoctor.get().getCorreoDoctor());
        doctor.setDuiDoctor(miDoctor.get().getDuiDoctor());
        doctor.setFechaRegistro(miDoctor.get().getFechaRegistro());
         edad = actualAge.getActualDate(miDoctor.get().getFechaNacimiento());
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public int getEdad() {
        return edad;
    }
}
