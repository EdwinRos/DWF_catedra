package sv.udb.edu.catedraframeworks.controllers;

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
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;
import sv.udb.edu.catedraframeworks.utils.HashSha1;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "doctoresAreaList")
@ELBeanName(value = "doctoresAreaList")
@Join(path = "/administradorInicio.do", to = "/supervisor_area/inicio.jsf")
public class supervisorController {

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);


    @Autowired
     private DoctorRepository doctorRepository;
     private List<Doctor> doctorList;
     Area area = new Area();
     Doctor doctor = new Doctor();

     String oldPassword;
     String newPassword;

     @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData(){
         int id = (int) session.getAttribute("id");  //session.getAttribute( "el identificador") para poder obtener el valor que se seteo en el obejto session
         Optional<Doctor> miDoctor = doctorRepository.findById(id); // que despues nos sirve para lo que nesecitemos
         doctor.setDoctorId(miDoctor.get().getDoctorId());
         doctor.setNombreDoctor(miDoctor.get().getNombreDoctor());
         doctor.setApellidoDoctor(miDoctor.get().getApellidoDoctor());
         doctor.setIdArea(miDoctor.get().getIdArea());
         doctor.setUsuario(miDoctor.get().getUsuario());
         doctor.setPassword(miDoctor.get().getPassword());
         doctor.setFechaNacimiento(miDoctor.get().getFechaNacimiento());
         doctor.setCorreoDoctor(miDoctor.get().getCorreoDoctor());
         doctor.setFechaRegistro(miDoctor.get().getFechaRegistro());
         doctor.setDuiDoctor(miDoctor.get().getDuiDoctor());
         doctor.setEstado(miDoctor.get().getEstado());
         area.setAreaId(doctor.getIdArea().getAreaId());

         doctorList = doctorRepository.findByIdAreaAndEstado(area,1);

     }

     public void changePassswrodRequest() throws NoSuchAlgorithmException {
         HashSha1 hashSha1 = new HashSha1();
         if(doctorRepository.findByPasswordAndDoctorId(hashSha1.hashPassword(oldPassword), doctor.getDoctorId()) != null){
             //ejecutar el cambio de contraseña
             doctor.setPassword(hashSha1.hashPassword(newPassword));
             doctorRepository.save(doctor);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion", "Su contraseña ha sido modificada! "));
         }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atencion", "La contraseña no coincide con la actual"));
         }

     }





    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public String inicio(){
         return "/supervisor_area/inicio.xhtml?faces-redirect=true";
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
