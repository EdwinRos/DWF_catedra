package sv.udb.edu.catedraframeworks.controllers;

import java.util.List;
import java.util.Optional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import javax.print.Doc;

@Scope(value = "session")
@Component(value = "AdminDoctorUpdate")
@ELBeanName(value = "AdminDoctorUpdate")
@Join(path = "/updatedoctor", to = "/administrador/administrador-doctor-modificar.jsf")
public class AdministradorDoctorUpdateController {


    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();


    @Autowired
    private AreaRepository areaRepository;
    List<Area> areas;
    Area area = new Area();

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadDoctor() {
        Optional<Doctor> miDoctor = doctorRepository.findById(Integer.valueOf(JsfUtil.getRequest().getParameter("IdDoctor")));
        doctor.setDoctorId(miDoctor.get().getDoctorId());
        doctor.setDuiDoctor(miDoctor.get().getDuiDoctor());
		doctor.setCorreoDoctor(miDoctor.get().getCorreoDoctor());
        doctor.setNombreDoctor(miDoctor.get().getNombreDoctor());
        doctor.setApellidoDoctor(miDoctor.get().getApellidoDoctor());
        doctor.setIdArea(miDoctor.get().getIdArea());
        doctor.setFechaRegistro(miDoctor.get().getFechaRegistro());
        doctor.setFechaNacimiento(miDoctor.get().getFechaNacimiento());
        doctor.setEstado(miDoctor.get().getEstado());
        doctor.setUsuario(miDoctor.get().getUsuario());
        doctor.setPassword(miDoctor.get().getPassword());
        loadAreas();
    }

    protected void loadAreas(){
        areas = areaRepository.findAll();
    }


    public String updateDoctor() {
        doctor.setIdArea(area);
        doctorRepository.save(doctor);
        return "/administrador-listado-doctores.xhtml?faces-redirect=true";
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public Area getArea() {
        return area;
    }

    public List<Area> getAreas() {
        return areas;
    }
}
