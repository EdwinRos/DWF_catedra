package sv.udb.edu.catedraframeworks.controllers.restController;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.repositories.CitasRepository;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.utils.HashSha1;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Component
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final CitasRepository citasRepository;

   @Autowired
    public PacienteService(PacienteRepository pacienteRepository, CitasRepository citasRepository) {
        this.pacienteRepository = pacienteRepository;
       this.citasRepository = citasRepository;
   }

    public List<Citas> getCitasPaciente(Paciente pacienteId){
       return  citasRepository.findCitasByIdPaciente(pacienteId);
    }

    public Paciente getPaciente(String password, String correo) throws NoSuchAlgorithmException {
        Paciente miPaciente = pacienteRepository.findByCorreoPacienteAndPassword(correo, hashString(password));
        if(miPaciente != null){
          return miPaciente;
        }else{
            return null;
        }
    }

    protected String hashString(String text) throws NoSuchAlgorithmException {
        HashSha1 sha1 = new HashSha1();
        String hasPassword = sha1.hashPassword(text);
        return hasPassword;
    }

    public void addNewPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }
}
