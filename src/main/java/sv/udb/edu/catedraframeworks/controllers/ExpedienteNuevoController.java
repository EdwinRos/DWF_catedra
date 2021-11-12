package sv.udb.edu.catedraframeworks.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "nuevoExpedienteController")
@ELBeanName(value = "nuevoExpedienteController")
@Join(path = "/expediente/nuevo", to = "/doctor/expediente-nuevo.jsf")
public class ExpedienteNuevoController {
	
	@Autowired
	private ExpedienteRepository expedienteRepository;
	Expediente expediente = new Expediente();
	
	@Autowired
	private PacienteRepository pacienteRepository;
	Paciente paciente = new Paciente();
	
	public String newRecord() {
		try {
			int idPaciente = Integer.parseInt(JsfUtil.getRequest().getParameter("pacienteId"));
			
			paciente = pacienteRepository.getById(idPaciente);
			
			expediente.setPacienteId(paciente);
			expedienteRepository.save(expediente);
			
			expediente = new Expediente();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro agregado", "Se agrego un nuevo registro al expediente"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al agregar registor", "Verifique que ingrese correctamente los datos"));
		}
		
		
		return "/doctor/home";
	}
	
	public Expediente getExpediente() {
		return expediente;
	}
}
