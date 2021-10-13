package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.utils.Emails;
import sv.udb.edu.catedraframeworks.utils.HashSha1;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;
import sv.udb.edu.catedraframeworks.utils.RamdomString;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Scope(value = "session")
@Component(value = "RecepcionistaRecuperarPasswordPacienteExitoso")
@ELBeanName(value = "RecepcionistaRecuperarPasswordPacienteExitoso")
@Join(path = "/recuperarpasswordpacienteexitoso", to="/recepcionista/recuperar-password-exitoso-paciente.jsf")
public class RecepcionistaRecuperarPasswordPacienteExitoso {

    @Autowired
    private PacienteRepository pacienteRepository;
    Paciente paciente = new Paciente();

    @Deferred
    @RequestAction
    @IgnorePostback
    public String recuperarPasswordPaciente() throws NoSuchAlgorithmException, MessagingException {
        String duiPaciente = JsfUtil.getRequest().getParameter("dui");
        paciente = pacienteRepository.findByDuiPaciente(duiPaciente);

        if(paciente == null){
            return "/recepcionista/recuperar-password-paciente.xhtml?faces-redirect=true";
        }else{
            HashSha1 hasSha1 = new HashSha1();
            RamdomString ramdomString = new RamdomString();
            String contraRamdom = ramdomString.password();

            paciente.setPassword(hasSha1.hashPassword(contraRamdom));

            SendMail(paciente, contraRamdom);

            pacienteRepository.save(paciente);
        }
        return null;
    }

    protected void SendMail(Paciente pac, String password) throws MessagingException{
        Emails emails = new Emails();
        emails.pacienteEmailRecuperacionContraseña(pac.getCorreoPaciente(), pac.getUsuario(), password, pac.getNombrePaciente(), pac.getApellidoPaciente());
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
