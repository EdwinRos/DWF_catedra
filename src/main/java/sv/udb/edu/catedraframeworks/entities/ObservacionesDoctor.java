package sv.udb.edu.catedraframeworks.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "observaciones_doctor")
public class ObservacionesDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "observaciones",
            nullable = false,
            columnDefinition = "VARCHAR(300)"
    )
    private String observacion;

    @Column(
            name = "fecha_registro",
            nullable = false
    )
    private Date fechaRegistro;
    @PrePersist
    protected void setFechaRegistro (){
        this.fechaRegistro = new Date();
    }

    @Column(
            name = "estado",
            nullable = false
    )
    private int estado;

    @ManyToOne
    @JoinColumn(
            name = "id_doctor",
            nullable = false
    )
    private Doctor idDoctor;


    //setters/getter/constructor


    public ObservacionesDoctor() {
    }

    public ObservacionesDoctor(String observacion, int estado, Doctor idDoctor) {
        this.observacion = observacion;
        this.estado = estado;
        this.idDoctor = idDoctor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Override
    public String toString() {
        return "ObservacionesDoctor{" +
                "id=" + id +
                ", observacion='" + observacion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado=" + estado +
                ", idDoctor=" + idDoctor +
                '}';
    }
}
