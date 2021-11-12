package sv.udb.edu.catedraframeworks.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import net.bytebuddy.utility.RandomString;
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;
import sv.udb.edu.catedraframeworks.utils.RamdomString;
import sv.udb.edu.catedraframeworks.utils.Emails;

@Scope(value = "session")
@Component(value = "agendarCita")
@ELBeanName(value = "agendarCita")
@Join(path = "/cita/proxima", to = "/doctor/agendar-cita.jsf")
public class AgendarCitaController {
	FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	@Autowired
	private PacienteRepository pacienteRepository;
	Paciente paciente = new Paciente();
	
	@Autowired
	private CitasRepository citasRepository;
	Citas cita = new Citas();
	
	@Autowired
	private DoctorRepository doctorRepository;
	private List<Doctor> doctores;
	
	String doctorCita = "";
	
	@Autowired
	private AreaRepository areaRepository;
	
	private RamdomString ramdomString;
    RamdomString stringRandom = new RamdomString();
	
	@Deferred
    @RequestAction
    @IgnorePostback
	public void loadData() {
		
		int idCita = Integer.parseInt(JsfUtil.getRequest().getParameter("citaId"));
		int idArea = Integer.parseInt(String.valueOf(session.getAttribute("areaId")));
		
		Area area = areaRepository.getById(idArea);
		
		Citas miCita = citasRepository.getById(idCita);
		paciente = pacienteRepository.getById(miCita.getIdPaciente().getPacienteId());
		doctores = doctorRepository.findByIdAreaAndEstado(area, 1);
	}
	
	public String proximaCita() throws ParseException, MessagingException {		
		String codigo = stringRandom.codigoCita();
		int idCita = Integer.parseInt(JsfUtil.getRequest().getParameter("citaId"));

		Date citaFecha = cita.getFechaCita();
		
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// Date citaFecha = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(cita.getFechaCita()));
		Date fechaActual = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		
		if (citaFecha.after(fechaActual)) {
			Doctor miDoctor = doctorRepository.getById(Integer.parseInt(doctorCita));
			
			cita.setCodigoCita(codigo);
			cita.setIdPaciente(paciente);
			cita.setIdDoctor(miDoctor);
			cita.setEstado(1);
			
			citasRepository.save(cita);
			
			cita = new Citas();
			
			Citas citaAnterior = citasRepository.getById(idCita);
			
			citaAnterior.setEstado(2);
			
			/*RamdomString ramdomString = new RamdomString();
	        String stringRandom = ramdomString.codigoCita();
	        
	        SendMail(paciente, stringRandom);*/
			
			citasRepository.save(citaAnterior);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proxima cita agendada con exito", "La proxixma cita del paciente fue registrada con exito"));
			
			return "/doctor/dashboard.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Fecha no puede ser la actual o anterior a la misma", "La fecha no puede se la actualo o anterior a la misma"));
			
			return "/cita/proxima?citaId=" + idCita + "&faces-redirect=true";
		}
	}
	
	protected void SendMail(Paciente pac, String codigoCitaPaciente) throws MessagingException{
        Emails emails = new Emails();
        emails.codigoCita(pac.getCorreoPaciente(), pac.getUsuario(), codigoCitaPaciente, pac.getNombrePaciente(), pac.getApellidoPaciente());
    }
	
	// Getters y setters
	public Paciente getPaciente() {
		return paciente;
	}
	
	public Citas getCita() {
		return cita;
	}
	
	public List<Doctor> getDoctores() {
		return doctores;
	}
	
	public String getDoctorCita() {
		return doctorCita;
	}
	
	public void setDoctorCita(String doctorCita) {
		this.doctorCita =  doctorCita;
	}
}
