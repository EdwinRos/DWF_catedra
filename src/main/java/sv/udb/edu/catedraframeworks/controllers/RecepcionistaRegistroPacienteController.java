package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.utils.Emails;
import sv.udb.edu.catedraframeworks.utils.HashSha1;
import sv.udb.edu.catedraframeworks.utils.RamdomString;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Scope(value = "session")
@Component(value = "PacienteController")
@ELBeanName(value = "PacienteController")
@Join(path = "/registropaciente", to="/recepcionista/registro-paciente.jsf")
public class RecepcionistaRegistroPacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();
    Paciente validacionPacienteDui = new Paciente();
    Paciente validacionPacienteCorreo = new Paciente();
    Paciente validacionPacienteTelefono = new Paciente();
    Paciente validacionPacienteUsuario = new Paciente();

    public void nuevoPaciente() throws NoSuchAlgorithmException, MessagingException {
        HashSha1 hasSha1 = new HashSha1();

        RamdomString ramdomString = new RamdomString();

        String contraRamdom = ramdomString.password();

        validacionPacienteDui = pacienteRepository.findByDuiPaciente(paciente.getDuiPaciente());
        validacionPacienteTelefono = pacienteRepository.findByTelefono(paciente.getTelefono());
        validacionPacienteCorreo = pacienteRepository.findByCorreoPaciente(paciente.getCorreoPaciente());
        validacionPacienteUsuario = pacienteRepository.findByUsuario(paciente.getUsuario());

        if(validacionPacienteDui != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "","Ya hay un paciente registrado con ese DUI"));
            validacionPacienteDui = new Paciente();
        }else if(validacionPacienteTelefono != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "","Ya hay un paciente registrado con ese telefono"));
            validacionPacienteTelefono = new Paciente();
        }else if(validacionPacienteCorreo != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "","Ya hay un paciente registrado con ese correo"));
            validacionPacienteCorreo = new Paciente();
        }else if(validacionPacienteUsuario != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "","Ya hay un paciente registrado con ese usuario"));
            validacionPacienteUsuario = new Paciente();
        }else{
            paciente.setEstado(1);

            paciente.setPassword(hasSha1.hashPassword(contraRamdom));

            SendMail(paciente, contraRamdom);

            pacienteRepository.save(paciente);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "","Paciente ingresado con exito"));

            paciente = new Paciente();
        }
    }

    protected void SendMail(Paciente pac, String password) throws MessagingException{
        Emails emails = new Emails();
        emails.pacienteEmail(pac.getCorreoPaciente(), pac.getUsuario(), password, pac.getNombrePaciente(), pac.getApellidoPaciente());
    }

    public Paciente getPaciente() {
        return paciente;
    }

}
