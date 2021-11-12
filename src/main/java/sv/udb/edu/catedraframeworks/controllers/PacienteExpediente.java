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
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.entities.Expediente;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.repositories.ExpedienteRepository;
import sv.udb.edu.catedraframeworks.utils.ActualAge;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "pacienteExpedienteController")
@ELBeanName(value = "pacienteExpedienteController")
@Join(path = "/doctor/paciente", to="/doctor/paciente-diagnostico.jsf")
public class PacienteExpediente {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	Paciente paciente = new Paciente();
	
	@Autowired
	private ExpedienteRepository expedienteRepository;
	List<Expediente> expediente;
	
	int edad = 0;

	@Deferred 
    @RequestAction
    @IgnorePostback
	public void loadData() {
		ActualAge edadActual = new ActualAge();
		 
		int idPaciente = Integer.valueOf(JsfUtil.getRequest().getParameter("pacienteId"));
		
		// paciente = pacienteRepository.getById(idPaciente);
		Optional<Paciente> miPaciente = pacienteRepository.findById(idPaciente);
		
        paciente.setPacienteId(miPaciente.get().getPacienteId());
        paciente.setDuiPaciente(miPaciente.get().getDuiPaciente());
        paciente.setCorreoPaciente(miPaciente.get().getCorreoPaciente());
        paciente.setNombrePaciente(miPaciente.get().getNombrePaciente());
        paciente.setApellidoPaciente(miPaciente.get().getApellidoPaciente());
        paciente.setTelefono(miPaciente.get().getTelefono());
        paciente.setFechaRegistro(miPaciente.get().getFechaRegistro());
        paciente.setEstado(miPaciente.get().getEstado());
        paciente.setSexo(miPaciente.get().getSexo());
        paciente.setFechaNacimiento(miPaciente.get().getFechaNacimiento());
		
		edad = edadActual.getActualDate(paciente.getFechaNacimiento());
		
		loadRecord(paciente);
	}
	
	protected void loadRecord(Paciente p) {
		expediente = expedienteRepository.findExpedienteByPacienteIdOrderByFechaRegistroDesc(p);
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public List<Expediente> getExpediente() {
		return expediente;
	}
	
	public int getEdad() {
		return edad;
	}
}
