package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer administradorId;

    @Column(
            name = "usuario",
            nullable = false,
            columnDefinition = "VARCHAR(70)",
            unique = true
    )
    private String usuario;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "VARCHAR(250)"
    )
    private String password;

    @Column(
            name = "fecha_registro",
            nullable = false,
            updatable = false
    )
    private Date fechaRegistro;

    @Column(
            name = "estado",
            nullable = false
    )
    private Integer estado;

    @PrePersist
    public void setFechaRegistro(){
        this.fechaRegistro = new Date();
    }

    public Administrador() {
    }

    public Administrador(String usuario, String password, Integer estado) {
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
    }

    public Integer getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Integer administradorId) {
        this.administradorId = administradorId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "administradorId=" + administradorId +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado=" + estado +
                '}';
    }
}
