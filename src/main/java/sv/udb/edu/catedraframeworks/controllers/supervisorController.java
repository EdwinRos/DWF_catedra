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

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;

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

     int prueba = 0;



     @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData(){
         prueba = (int) session.getAttribute("id");
         area.setAreaId(1);
         doctorList = doctorRepository.findByIdArea(area);

     }





    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public String inicio(){
         return "/supervisor_area/inicio.xhtml?faces-redirect=true";
    }

    public int getPrueba() {
        return prueba;
    }
}
