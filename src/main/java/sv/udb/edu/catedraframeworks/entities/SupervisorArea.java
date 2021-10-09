package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "supervisor_area")
public class SupervisorArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctoresAreaId;

    @Column(
            name = "estado",
            nullable = false
    )
    private Integer estado;

    @Column(
            name = "fecha_registro",
            nullable = false,
            updatable = false
    )
    private Date fechaRegistro;

    @PrePersist
    protected void setFechaRegistro() {
        this.fechaRegistro = new Date();
    }

    @ManyToOne
    @JoinColumn(
            name = "id_area",
            nullable = false
    )
    private Area idArea;

    @ManyToOne
    @JoinColumn(
            name = "id_doctor",
            nullable = false
    )
    private Doctor idDoctor;

//    getters/setters/ constructors


    public SupervisorArea() {
    }

    public SupervisorArea(Integer estado, Area idArea, Doctor idDoctor) {
        this.estado = estado;
        this.idArea = idArea;
        this.idDoctor = idDoctor;
    }

    public Integer getDoctoresAreaId() {
        return doctoresAreaId;
    }

    public void setDoctoresAreaId(Integer doctoresAreaId) {
        this.doctoresAreaId = doctoresAreaId;
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
        return "DocotoresArea{" +
                "doctoresAreaId=" + doctoresAreaId +
                ", estado=" + estado +
                ", fechaRegistro=" + fechaRegistro +
                ", idArea=" + idArea +
                ", idDoctor=" + idDoctor +
                '}';
    }
}
