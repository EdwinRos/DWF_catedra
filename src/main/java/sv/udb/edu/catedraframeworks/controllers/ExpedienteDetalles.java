package sv.udb.edu.catedraframeworks.controllers;

import java.io.Console;
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
import sv.udb.edu.catedraframeworks.entities.Expediente;
import sv.udb.edu.catedraframeworks.repositories.ExpedienteRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "detallesExpediente")
@ELBeanName(value = "detallesExpediente")
@Join(path = "/expediente/ver", to="/doctor/expediente-detalles.jsf")
public class ExpedienteDetalles { 
	
	@Autowired
	private ExpedienteRepository expedienteRepository;
	Expediente expediente;
	
	@Deferred
    @RequestAction
    @IgnorePostback 
	public void loadData() {
		int idExpediente = Integer.valueOf(JsfUtil.getRequest().getParameter("expedienteId"));
		
		expediente = expedienteRepository.getById(idExpediente);
		/*
		 * Optional<Expediente> miExpediente = expedienteRepository.findById(idExpediente);
		
		expediente.setExpedienteId(miExpediente.get().getExpedienteId());		
		expediente.s(miExpediente.get().getExpedienteId());		
		expediente.setExpedienteId(miExpediente.get().getExpedienteId());		
		expediente.setExpedienteId(miExpediente.get().getExpedienteId());		
		expediente.setExpedienteId(miExpediente.get().getExpedienteId());
		 * 
		 * */
	}
	
	public Expediente getExpediente() {
		return expediente;
	}
}
