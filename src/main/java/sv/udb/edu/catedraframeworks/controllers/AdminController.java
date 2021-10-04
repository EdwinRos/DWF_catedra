package sv.udb.edu.catedraframeworks.controllers;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sv.udb.edu.catedraframeworks.entities.Administrador;
import sv.udb.edu.catedraframeworks.repositories.AdministradorRepository;

@Scope(value = "session")
@Component(value = "adminControler")
@ELBeanName(value = "adminControler")
@Join(path = "/inicio", to="/admin-form.jsf")
public class AdminController {

    @Autowired
    private AdministradorRepository administradorRepository;
    Administrador administrador = new Administrador();

    public String save(){
        administrador.setEstado(1);
        administradorRepository.save(getAdministrador());
        administrador = new Administrador();

        return  "/list.xhtml?faces-redirect=true";
    }

    public Administrador getAdministrador() {
        return administrador;
    }


}
