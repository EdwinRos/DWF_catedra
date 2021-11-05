package sv.udb.edu.catedraframeworks.controllers.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sv.udb.edu.catedraframeworks.entities.Citas;
import sv.udb.edu.catedraframeworks.entities.Paciente;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(path = "/DWF/v1/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @GetMapping(path = "/citas/{id}")
    public List<Citas> getPacientes(@PathVariable String id){
        Paciente miPaciente = new Paciente();
        miPaciente.setPacienteId(Integer.valueOf(id));
        return pacienteService.getCitasPaciente(miPaciente);
    }

    @GetMapping(path = "/login/{correo}/{password}")
    public Paciente loginPaciente(@PathVariable String correo, @PathVariable String password) throws NoSuchAlgorithmException {
        return pacienteService.getPaciente(password, correo);
    }

    @PostMapping
    public void registerNewPaciente(@RequestBody Paciente paciente){
        pacienteService.addNewPaciente(paciente);
    }
}
