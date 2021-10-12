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
import sv.udb.edu.catedraframeworks.utils.RamdomString;

import javax.faces.context.FacesContext;
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

    int idpaciente = 0;


    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    Recepcionista recepcionista = new Recepcionista();

    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();

    @Autowired
    private CitasRepository citasRepository;
    Citas cita = new Citas();

    Area area = new Area();

    private RamdomString ramdomString;
    RamdomString stringRandom = new RamdomString();

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

    public String registrarCita(){
        String contraRamdom = ramdomString.codigoCita();
        cita.setCodigoCita(contraRamdom);
        cita.setEstado(1);
        cita.setExtras(cita.getExtras());
        cita.setFechaCita(cita.getFechaCita());
        cita.setHoraCita(cita.getHoraCita());
        cita.setTitulo(cita.getTitulo());
        cita.setIdDoctor(cita.getIdDoctor());
        paciente = pacienteRepository.findByDuiPaciente(getDui());
        cita.setIdPaciente(paciente);

        citasRepository.save(cita);

        cita = new Citas();

        return "/recepcionista/inicio.xhtml?faces-redirect=true";
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

}
