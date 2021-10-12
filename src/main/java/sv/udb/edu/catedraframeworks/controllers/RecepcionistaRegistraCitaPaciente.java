package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.utils.RamdomString;

import java.util.List;

@Scope(value = "session")
@Component(value = "RecepcionistaRegistraCitaPaciente")
@ELBeanName(value = "RecepcionistaRegistraCitaPaciente")
@Join(path = "/recepcion/registrarcita", to="/recepcionista/registro-cita-paciente.jsf")
public class RecepcionistaRegistraCitaPaciente {
    String dui = "";

    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();

    @Autowired
    private AreaRepository areaRepository;
    Area area = new Area();

    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();

    @Autowired
    private CitasRepository citasRepository;
    Citas cita = new Citas();

    private RamdomString ramdomString;
    RamdomString stringRandom = new RamdomString();

    @Deferred
    @RequestAction
    @IgnorePostback
    public List mostrarDoctores(){



        return mostrarDoctores();
    }

    public void registrarCita(){

    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }


}
