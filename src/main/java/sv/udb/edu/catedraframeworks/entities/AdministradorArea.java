package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Date;

@Entity
@Table(name = "administrador_area")
public class AdministradorArea {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer administradorId;

    @Column(
            name = "estado",
            nullable = false
    )
    private Integer estado;

    @Column(
            name = "fecha_registro",
            nullable = false
    )
    private Date fechaRegistro;
    @PrePersist
    protected void setFechaRegistro(){
        this.fechaRegistro = new Date();
    }

    @OneToOne
    @JoinColumn(
            name = "id_area",
            nullable = false
    )
    private Area idArea;

    @OneToOne
    @JoinColumn(
            name = "id_doctor",
            nullable = false
    )
    private Doctor idDoctor;
    //getters/ setters / constructors


    public AdministradorArea() {
    }

    public AdministradorArea(Integer estado, Area idArea, Doctor idDoctor) {
        this.estado = estado;
        this.idArea = idArea;
        this.idDoctor = idDoctor;
    }

    public Integer getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Integer administradorId) {
        this.administradorId = administradorId;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Override
    public String toString() {
        return "AdministradorArea{" +
                "administradorId=" + administradorId +
                ", estado=" + estado +
                ", fechaRegistro=" + fechaRegistro +
                ", idArea=" + idArea +
                ", idDoctor=" + idDoctor +
                '}';
    }
}
