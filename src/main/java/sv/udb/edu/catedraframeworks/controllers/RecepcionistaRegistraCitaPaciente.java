package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.*;
import sv.udb.edu.catedraframeworks.repositories.*;
import sv.udb.edu.catedraframeworks.utils.Emails;
import sv.udb.edu.catedraframeworks.utils.RamdomString;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "RecepcionistaRegistraCitaPaciente")
@ELBeanName(value = "RecepcionistaRegistraCitaPaciente")
@Join(path = "/recepcionista/registrarcita", to="/recepcionista/registro-cita-paciente.jsf")
public class RecepcionistaRegistraCitaPaciente {

    String dui = "";

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();

    @Autowired
    private CitasRepository citasRepository;
    Citas cita = new Citas();

    Area area = new Area();

    List<Doctor> misDoctores;


    @Deferred
    @RequestAction
    @IgnorePostback
    public void mostrarDoctores(){
        int id = (int) session.getAttribute("id");

        Optional<Recepcionista> miRecepcionista = recepcionistaRepository.findById(id);

        area = miRecepcionista.get().getAreaId();

        misDoctores = doctorRepository.findByIdAreaAndEstado(area, 1);
    }

    public String registrarCita() throws NoSuchAlgorithmException, MessagingException{

        RamdomString ramdomString = new RamdomString();
        String stringRandom = ramdomString.codigoCita();

        cita.setCodigoCita(stringRandom);
        cita.setEstado(1);

        paciente = pacienteRepository.findByDuiPaciente(getDui());

        if(paciente == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion","DUI no encontrado"));
            return null;
        }else{
            cita.setIdDoctor(doctor);

            cita.setIdPaciente(paciente);

            SendMail(paciente, stringRandom);

            citasRepository.save(cita);

            return "/recepcionista/inicio.xhtml?registro=si&faces-redirect=true";
        }
    }

    protected void SendMail(Paciente pac, String codigoCitaPaciente) throws MessagingException{
        Emails emails = new Emails();
        emails.codigoCita(pac.getCorreoPaciente(), pac.getUsuario(), codigoCitaPaciente, pac.getNombrePaciente(), pac.getApellidoPaciente());
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public List<Doctor> getMisDoctores() {
        return misDoctores;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Citas getCita() {
        return cita;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
