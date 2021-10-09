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



    public String nuevoPaciente() throws NoSuchAlgorithmException, MessagingException {
        HashSha1 hasSha1 = new HashSha1();

        RamdomString ramdomString = new RamdomString();

        String contraRamdom = ramdomString.password();

        paciente.setEstado(1);

        paciente.setPassword(hasSha1.hashPassword(contraRamdom));

        SendMail(paciente, contraRamdom);

        pacienteRepository.save(paciente);

        paciente = new Paciente();

        return "/inicio";
    }

    protected void SendMail(Paciente pac, String password) throws MessagingException{
        Emails emails = new Emails();
        emails.pacienteEmail(pac.getCorreoPaciente(), pac.getUsuario(), password, pac.getNombrePaciente(), pac.getApellidoPaciente());
    }

    public Paciente getPaciente() {
        return paciente;
    }

}
