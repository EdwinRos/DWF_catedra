package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;

import java.util.List;

@Scope(value = "session")
@Component(value = "RecepcionistaRegistroPacienteController")
@ELBeanName(value = "RecepcionistaRegistroPacienteController")
@Join(path = "/registropaciente", to="/recepcionista/registro-paciente.jsf")
public class RecepcionistaRegistroPacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();

    private List<Paciente> listarPaciente() {
        return null;
    }


}
