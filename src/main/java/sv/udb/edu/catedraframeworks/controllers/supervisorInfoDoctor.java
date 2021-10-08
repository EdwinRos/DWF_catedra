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
import sv.udb.edu.catedraframeworks.entities.ObservacionPacienteDoctor;
import sv.udb.edu.catedraframeworks.entities.ObservacionesDoctor;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.repositories.ObservacionPacienteDoctorRepository;
import sv.udb.edu.catedraframeworks.repositories.ObservacionesDoctorRepository;
import sv.udb.edu.catedraframeworks.utils.ActualAge;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    @Autowired
    private ObservacionesDoctorRepository observacionesDoctorRepository;
    ObservacionesDoctor observacionesDoctor = new ObservacionesDoctor();

    @Autowired
    private ObservacionPacienteDoctorRepository observacionPacienteDoctorRepository;
    List<ObservacionPacienteDoctor> observacionPacienteDoctorList;
    int nota = 0;




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

         //carga de datos al doctor
         loadCitasDelDoctor(doctor);
         loadRating(doctor);


    }



    public void enviarMensaje(){
        observacionesDoctor.setIdDoctor(doctor);
        observacionesDoctor.setEstado(1);
        observacionesDoctorRepository.save(observacionesDoctor);
        observacionesDoctor = new ObservacionesDoctor();
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito","Mensaje enviado!!"));
    }



    protected void loadRating(Doctor dr){
        int total =0;
        observacionPacienteDoctorList = observacionPacienteDoctorRepository.getObservacionPacienteDoctorByIdDoctor(dr);

        for (ObservacionPacienteDoctor items: observacionPacienteDoctorList
             ) {
             total += items.getRecordDoctor();
        }

        nota = total/observacionPacienteDoctorList.size();


    }

    public List<ObservacionPacienteDoctor> getObservacionPacienteDoctorList() {
        return observacionPacienteDoctorList;
    }

    public int getNota() {
        return nota;
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

    public ObservacionesDoctor getObservacionesDoctor() {
        return observacionesDoctor;
    }

}
