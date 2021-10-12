package sv.udb.edu.catedraframeworks.controllers.supervisor;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Expediente;
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.ExpedienteRepository;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.utils.ActualAge;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import java.util.List;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "pacienteAd")
@ELBeanName(value = "pacienteAd")
@Join(path = "/perfilpacientesupervisor", to = "/supervisor_area/perfilpaciente.jsf")
public class SupervisorPaciente {

    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();
     int edad = 0;

    ActualAge edadActual = new ActualAge();

    @Autowired
    private ExpedienteRepository expedienteRepository;
    List<Expediente> expedientes;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadPaciente() {
        Optional<Paciente> miPaciente = pacienteRepository.findById(Integer.valueOf(JsfUtil.getRequest().getParameter("idPaciente")));
        paciente.setPacienteId(miPaciente.get().getPacienteId());
        paciente.setDuiPaciente(miPaciente.get().getDuiPaciente());
        paciente.setCorreoPaciente(miPaciente.get().getCorreoPaciente());
        paciente.setNombrePaciente(miPaciente.get().getNombrePaciente());
        paciente.setApellidoPaciente(miPaciente.get().getApellidoPaciente());
        paciente.setTelefono(miPaciente.get().getTelefono());
        paciente.setFechaRegistro(miPaciente.get().getFechaRegistro());
        paciente.setEstado(miPaciente.get().getEstado());
        paciente.setSexo(miPaciente.get().getSexo());
        edad = edadActual.getActualDate(miPaciente.get().getFechaNacimiento());

        loadListOfRecords(paciente);

    }

    protected void loadListOfRecords(Paciente p) {
        expedientes = expedienteRepository.findExpedienteByPacienteId(p);
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public int getEdad() {
        return edad;
    }

    public List<Expediente> getExpedientes() {
        return expedientes;
    }
}
