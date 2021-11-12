package sv.udb.edu.catedraframeworks.controllers.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sv.udb.edu.catedraframeworks.entities.*;

import javax.mail.MessagingException;
import javax.ws.rs.HeaderParam;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(path = "/DWF/v1/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @GetMapping(path = "/citas/{id}")
    public List<Citas> getPacientes(@PathVariable Integer id){
        Paciente miPaciente = new Paciente();
        miPaciente.setPacienteId(id);
        return pacienteService.getCitasVigentes(miPaciente);
    }

    @GetMapping(path = "/citas/tomadas/{id}")
    public List<Citas> getCitasTomadas(@PathVariable Integer id){
        Paciente miPaciente = new Paciente();
        miPaciente.setPacienteId(id);
        return  pacienteService.getCitasTomadas(miPaciente);
    }

    @PostMapping(path = "/login")
    public Paciente loginPaciente(@RequestBody Paciente paciente) throws NoSuchAlgorithmException {
        return pacienteService.getPaciente(paciente.getPassword(), paciente.getUsuario());
    }

    @PostMapping(path = "/registro")
    public String registerNewPaciente(@RequestBody Paciente paciente) throws NoSuchAlgorithmException, MessagingException {
        pacienteService.addNewPaciente(paciente);
        return "Registrado";
    }

    @PostMapping(path = "/registro/cita")
    public Citas registerNewCita(@RequestBody Citas citas){
        pacienteService.addNewCita(citas);
        return citas;
    }

    @PostMapping(path = "/observacion")
    public String registerNewObservacion(@RequestBody ObservacionPacienteDoctor observacionPacienteDoctor){
        pacienteService.addNewObservacion(observacionPacienteDoctor);
        return "Gracias por sus comentarios";
    }

    @GetMapping(path = "/areas")
    public List<Area>getAreas(){
        return pacienteService.getAreas();
    }

    @GetMapping(path = "/doctores/{idArea}")
    public List<Doctor>getDoctoresByArea(@PathVariable String idArea){
        return pacienteService.getDoctoresByArea(idArea);
    }

    @GetMapping(path = "/expediente/{id}")
    public List<Expediente>getExpedientesByPaciente(@PathVariable Integer id){
        return pacienteService.getExpedientesByPacienteId(id);
    }

        @PostMapping(path = "/cita/revisada")
    public void citaRevisadaPorPaciente(@RequestBody Citas cita){
        pacienteService.citaRevisadaPorPaciente(cita);
    }

}
