package sv.udb.edu.catedraframeworks.controllers;

import java.util.Optional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.utils.ActualAge;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "citaDetallesController")
@ELBeanName(value = "citaDetallesController")
@Join(path = "/cita/detalles", to="/doctor/detalles-cita.jsf")
public class CitaDetallesController {
	@Autowired
    private CitasRepository citaRepository;
    Citas cita = new Citas();
    int edad = 0;
    
    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData() {
    	ActualAge edadPaciente = new ActualAge();
    	int idCita = Integer.valueOf(JsfUtil.getRequest().getParameter("citaId"));
    	
    	Optional<Citas> miCita = citaRepository.findById(idCita);
    	
    	cita.setCitasId(miCita.get().getCitasId());
    	cita.setCodigoCita(miCita.get().getCodigoCita());
    	cita.setTitulo(miCita.get().getTitulo());
    	cita.setExtras(miCita.get().getExtras());
    	cita.setFechaCita(miCita.get().getFechaCita());
    	cita.setHoraCita(miCita.get().getHoraCita());
    	cita.setIdPaciente(miCita.get().getIdPaciente());
    	
    	edad = edadPaciente.getActualDate(miCita.get().getIdPaciente().getFechaNacimiento());
    }
    
    public String finishAppointment() { 	
    	Citas miCita = new Citas();
    	
    	miCita = citaRepository.getById(cita.getCitasId());
    	
    	miCita.setEstado(2);
    	citaRepository.save(miCita);
    	
    	return "/doctor/dashboard.xhtml?faces-redirect=true";
    }
    
    public Citas getCita() {
        return cita;
    }

    public int getEdad() {
        return edad;
    }
}
