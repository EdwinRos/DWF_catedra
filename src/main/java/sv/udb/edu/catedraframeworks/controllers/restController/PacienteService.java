package sv.udb.edu.catedraframeworks.controllers.restController;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.*;
import sv.udb.edu.catedraframeworks.repositories.*;
import sv.udb.edu.catedraframeworks.utils.Emails;
import sv.udb.edu.catedraframeworks.utils.HashSha1;
import sv.udb.edu.catedraframeworks.utils.RamdomString;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Component
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final CitasRepository citasRepository;

    private final ObservacionPacienteDoctorRepository observacionPacienteDoctorRepository;

    private final AreaRepository areaRepository;

    private final DoctorRepository doctorRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, CitasRepository citasRepository, ObservacionPacienteDoctorRepository observacionPacienteDoctorRepository, AreaRepository areaRepository, DoctorRepository doctorRepository) {
        this.pacienteRepository = pacienteRepository;
        this.citasRepository = citasRepository;
        this.observacionPacienteDoctorRepository = observacionPacienteDoctorRepository;
        this.areaRepository = areaRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Citas> getCitasPaciente(Paciente pacienteId) {
        return citasRepository.findCitasByIdPaciente(pacienteId);
    }

    public Paciente getPaciente(String password, String correo) throws NoSuchAlgorithmException {
        Paciente miPaciente = pacienteRepository.findByCorreoPacienteAndPassword(correo, hashString(password));
        if (miPaciente != null) {
            return miPaciente;
        } else {
            return null;
        }
    }

    public void addNewPaciente(Paciente paciente) throws NoSuchAlgorithmException, MessagingException {
        Emails pacienteEmail = new Emails();
        String passwordPaciente = ramdomPassword();
        pacienteEmail.pacienteEmail(paciente.getCorreoPaciente(), paciente.getUsuario(), passwordPaciente, paciente.getNombrePaciente(), paciente.getApellidoPaciente());
        paciente.setPassword(hashString(passwordPaciente));
        pacienteRepository.save(paciente);
    }

    public void addNewCita(Citas citas) {
        //faltaria ver lo de las validaciones
        citasRepository.save(citas);
    }

    public void addNewObservacion(ObservacionPacienteDoctor observacionPacienteDoctor) {
        observacionPacienteDoctorRepository.save(observacionPacienteDoctor);
    }


    public List<Area> getAreas() {
        return areaRepository.findAll();
    }

    public List<Doctor> getDoctoresByArea(String idArea) {
        Area drArea = new Area();
        drArea.setAreaId(Integer.valueOf(idArea));
        return doctorRepository.findByIdAreaAndEstado(drArea, 1);
    }

    protected String hashString(String text) throws NoSuchAlgorithmException {
        HashSha1 sha1 = new HashSha1();
        String hasPassword = sha1.hashPassword(text);
        return hasPassword;
    }

    protected String ramdomPassword(){
        RamdomString ramdomString = new RamdomString();
        return ramdomString.password();
    }

}
