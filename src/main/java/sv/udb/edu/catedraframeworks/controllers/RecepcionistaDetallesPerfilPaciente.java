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
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

@Scope(value = "session")
@Component(value = "RecepcionistaDetallesPerfilPaciente")
@ELBeanName(value = "RecepcionistaDetallesPerfilPaciente")
@Join(path = "/detallesperfilpaciente", to="/recepcionista/detalles-perfil-paciente.jsf")
public class RecepcionistaDetallesPerfilPaciente {
    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();

    @Deferred
    @RequestAction
    @IgnorePostback
    public void cargarDetallesPaciente() {
        String duiPaciente = JsfUtil.getRequest().getParameter("dui");
        paciente = pacienteRepository.findByDuiPaciente(duiPaciente);
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
