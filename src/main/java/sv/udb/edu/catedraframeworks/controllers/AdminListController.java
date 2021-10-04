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
import sv.udb.edu.catedraframeworks.repositories.AdministradorRepository;

import java.util.List;

@Scope(value = "session")
@Component(value = "adminList")
@ELBeanName(value = "adminList")
@Join(path = "/listado", to = "/list.jsf")
public class AdminListController {

    @Autowired
    private AdministradorRepository administradorRepository;
    private List<Administrador> administradors;

    @Deferred
    @RequestAction
    @IgnorePostback
    public void loadData(){
        administradors = administradorRepository.findAll();
    }

    public List<Administrador> getAdministradors() {
        return administradors;
    }
}
