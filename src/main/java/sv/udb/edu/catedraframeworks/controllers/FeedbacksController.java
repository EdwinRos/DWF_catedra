package sv.udb.edu.catedraframeworks.controllers;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
import sv.udb.edu.catedraframeworks.entities.ObservacionesDoctor;
import sv.udb.edu.catedraframeworks.repositories.ObservacionesDoctorRepository;
import sv.udb.edu.catedraframeworks.entities.ObservacionPacienteDoctor;
import sv.udb.edu.catedraframeworks.repositories.ObservacionPacienteDoctorRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "feedbackController")
@ELBeanName(value = "feedbackController")
@Join(path = "/doctor/comentarios", to = "/doctor/comentarios.jsf")
public class FeedbacksController {
	FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	@Autowired
	private ObservacionesDoctorRepository observacionesDoctorRepository;
	private List<ObservacionesDoctor> observacionesDoctor;
	
	@Autowired
	private DoctorRepository doctorRepository;
	Doctor doctor = new Doctor();
	
	@Autowired
	private ObservacionPacienteDoctorRepository observacionesPacientesRepository;
	private List<ObservacionPacienteDoctor> observacionesPacientes;
	
	@Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
		int idDoctor = Integer.parseInt(String.valueOf(session.getAttribute("doctorId")));
		
		Doctor doctor = doctorRepository.getById(idDoctor);
		
		observacionesSupervisor(doctor);
		observacionesPaciente(doctor);
	}
	
	protected void observacionesSupervisor(Doctor doctor) {
		observacionesDoctor = observacionesDoctorRepository.findObservacionesDoctorByIdDoctorOrderByFechaRegistroDesc(doctor);
	}
	
	protected void observacionesPaciente(Doctor doctor) {
		observacionesPacientes = observacionesPacientesRepository.findObservacionPacienteDoctorByIdDoctorOrderByFechaRegistroDesc(doctor);
	}
	
	// Getters
	public List<ObservacionesDoctor> getObservacionesDoctor() {
		return observacionesDoctor;
	}
	
	public List<ObservacionPacienteDoctor> getObservacionesPacientes() {
		return observacionesPacientes;
	}
}

