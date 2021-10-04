package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Area;
import sv.udb.edu.catedraframeworks.entities.Doctor;
import sv.udb.edu.catedraframeworks.repositories.DoctorRepository;

import java.util.Arrays;
import java.util.List;

@Scope(value = "session")
@Component(value = "doctoresAreaList")
@ELBeanName(value = "doctoresAreaList")
@Join(path = "/administradorInicio", to = "/supervisor_area/inicio.jsf")
public class supervisorController {

    @Autowired
     private DoctorRepository doctorRepository;
     private List<Doctor> doctorList;
     Area area = new Area();

     @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData(){
         area.setAreaId(2);
         doctorList = doctorRepository.findByIdArea(area);
     }


    public List<Doctor> getDoctorList() {
        return doctorList;
    }
}
