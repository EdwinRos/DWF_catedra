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
import sv.udb.edu.catedraframeworks.entities.Paciente;
import sv.udb.edu.catedraframeworks.entities.Recepcionista;
import sv.udb.edu.catedraframeworks.repositories.AreaRepository;
import sv.udb.edu.catedraframeworks.repositories.PacienteRepository;
import sv.udb.edu.catedraframeworks.repositories.RecepcionistaRepository;
import sv.udb.edu.catedraframeworks.utils.JsfUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Scope(value = "session")
@Component(value = "RecepcionistaInicio")
@ELBeanName(value = "RecepcionistaInicio")
@Join(path = "/recepcioninicio", to="/recepcionista/inicio.jsf")
public class RecepcionistaInicio {

    String registro = "";

    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    Recepcionista recepcionista = new Recepcionista();

    @Autowired
    private AreaRepository areaRepository;
    Area area = new Area();

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData(){

        registro = JsfUtil.getRequest().getParameter("registro");

        int id = (int) session.getAttribute("id");
        Optional<Recepcionista> miRecepcionista = recepcionistaRepository.findById(id);

        recepcionista.setRecepcionistaId(miRecepcionista.get().getRecepcionistaId());
        recepcionista.setApellidoRecepcionista(miRecepcionista.get().getApellidoRecepcionista());
        recepcionista.setEstado(miRecepcionista.get().getEstado());
        recepcionista.setFechaRegistro(miRecepcionista.get().getFechaRegistro());
        recepcionista.setNombreRecepcionista(miRecepcionista.get().getNombreRecepcionista());
        recepcionista.setPassword(miRecepcionista.get().getPassword());
        recepcionista.setUsuario(miRecepcionista.get().getUsuario());
        recepcionista.setAreaId(miRecepcionista.get().getAreaId());

        area = recepcionista.getAreaId();
        Optional<Area> miArea = areaRepository.findById(area.getAreaId());

        area.setAreaId(miArea.get().getAreaId());
        area.setEstado(miArea.get().getEstado());
        area.setFechaRegistro(miArea.get().getFechaRegistro());
        area.setNombreArea(miArea.get().getNombreArea());

        if(registro != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atencion","Operacion realizada con exito"));
        }
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public Area getArea(){
        return area;
    }
}
