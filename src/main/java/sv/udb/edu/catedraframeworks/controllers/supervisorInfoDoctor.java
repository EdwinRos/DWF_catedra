package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.Parameter;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.ActualAge;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import java.util.List;
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


    @Autowired
    private CitasRepository citasRepository;
    private List<Citas> citas ;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadDoctor(){

        ActualAge actualAge = new ActualAge();

        Optional<Doctor> miDoctor = doctorRepository.findById( Integer.valueOf(JsfUtil.getRequest().getParameter("drId")));
        doctor.setDoctorId(miDoctor.get().getDoctorId());
        doctor.setNombreDoctor(miDoctor.get().getNombreDoctor());
        doctor.setApellidoDoctor(miDoctor.get().getApellidoDoctor());
        doctor.setIdArea(miDoctor.get().getIdArea());
        doctor.setEstado(miDoctor.get().getEstado());
        doctor.setCorreoDoctor(miDoctor.get().getCorreoDoctor());
        doctor.setDuiDoctor(miDoctor.get().getDuiDoctor());
        doctor.setFechaRegistro(miDoctor.get().getFechaRegistro());
         edad = actualAge.getActualDate(miDoctor.get().getFechaNacimiento());
         loadCitasDelDoctor(doctor);
    }

    protected  void loadCitasDelDoctor(Doctor doctor){
        citas = citasRepository.findCitasByIdDoctor(doctor);
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
