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
import sv.udb.edu.catedraframeworks.entities.Recepcionista;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import javax.print.Doc;
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
    public String cargarDetallesCita() {
        String codigoCita = JsfUtil.getRequest().getParameter("codigo");

        cita = citaRepository.findByCodigoCita(codigoCita);

        if(cita == null){
            return "/recepcionista/consultar-cita-paciente.xhtml?faces-redirect=true";
        }
        return null;
    }

    public Citas getCita() {
        return cita;
    }
}
