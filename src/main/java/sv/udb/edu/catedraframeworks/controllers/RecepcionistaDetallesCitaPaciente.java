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
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import java.util.List;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "RecepcionistaDetallesCitaController")
@ELBeanName(value = "RecepcionistaDetallesCitaController")
@Join(path = "/detallescitapaciente", to="/recepcionista/detalles-cita-paciente.jsf")
public class RecepcionistaDetallesCitaPaciente {
    @Autowired
    private CitasRepository citaRepository;
    Citas cita = new Citas();

    @Deferred
    @RequestAction
    @IgnorePostback
    public void cargarDetallesCita() {
        String codigoCita = JsfUtil.getRequest().getParameter("codigo");

        /*Optional<Citas> miCita = Optional.ofNullable(citaRepository.findByCodigoCita(codigoCita));

        cita.setCitasId(miCita.get().getCitasId());
        cita.setCodigoCita(miCita.get().getCodigoCita());
        cita.setTitulo(miCita.get().getTitulo());
        cita.setExtras(miCita.get().getExtras());
        cita.setFechaCita(miCita.get().getFechaCita());
        cita.setHoraCita(miCita.get().getHoraCita());
        cita.setDui_paciente(miCita.get().getDui_paciente());
        cita.setIdPaciente(miCita.get().getIdPaciente());*/

        cita = citaRepository.findByCodigoCita(codigoCita);
    }

    public Citas getCita() {
        return cita;
    }
}
