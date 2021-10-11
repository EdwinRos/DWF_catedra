package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.utils.ActualAge;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import java.util.Optional;

@Scope(value = "session")
@Component(value = "RecepcionistaConsultarCitaController")
@ELBeanName(value = "RecepcionistaConsultarCitaController")
@Join(path = "/consultarcita", to="/recepcionista/consultar-cita-paciente.jsf")

public class RecepcionistaConsultarCita {

}
