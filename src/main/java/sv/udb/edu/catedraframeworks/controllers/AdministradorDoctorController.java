package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sv.udb.edu.catedraframeworks.entities.Administrador;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.Emails;
import sv.udb.edu.catedraframeworks.utils.HashSha1;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;
import sv.udb.edu.catedraframeworks.utils.RamdomString;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Scope(value = "session")
@Component(value = "AdminDoctorControler")
@ELBeanName(value = "AdminDoctorControler")
@Join(path = "/administradoringresardoctor", to = "/administrador/ingresardoctor.jsf")
public class AdministradorDoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    Doctor doctor = new Doctor();

    @Autowired
    private AreaRepository areaRepository;
    private List<Area> areas;

    Area area = new Area();


    public String nuevoDoctor() throws NoSuchAlgorithmException, MessagingException {
        HashSha1 hasSha1 = new HashSha1();
        RamdomString ramdomString = new RamdomString();

        String contraRamdom = ramdomString.password();

        doctor.setEstado(1);
        doctor.setIdArea(area);
        doctor.setPassword(hasSha1.hashPassword(contraRamdom));

        SendMail(doctor, contraRamdom);

        doctorRepository.save(doctor);


        doctor = new Doctor();

        return "/ingresardoctor.xhtml?faces-redirect=true";

    }

    protected void SendMail(Doctor doc, String password) throws MessagingException{
        Emails emails = new Emails();
        emails.docotorEmail(doc.getCorreoDoctor(), doc.getUsuario(), password, doc.getNombreDoctor(), doc.getApellidoDoctor());
    }

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadAreas() {
        areas = areaRepository.findAll();
    }


    public String deleteDoctor() {
		  int id = Integer.valueOf(JsfUtil.getRequest().getParameter("idDoctor"));
		  doctorRepository.deleteById(id);
		  doctor = new Doctor();
		  
		  return "/administrador-listado-doctores.xhtml?faces-redirect=true";
	  }
	  


    public Doctor getDoctor() {
        return doctor;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public Area getArea() {
        return area;
    }
}
