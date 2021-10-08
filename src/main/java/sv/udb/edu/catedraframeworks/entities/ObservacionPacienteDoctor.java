package sv.udb.edu.catedraframeworks.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paciente_observaciones")
public class ObservacionPacienteDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "calificacion_doctor",
            nullable = true
    )
    private int recordDoctor;

    @Column(
            name = "observacion",
            columnDefinition = "VARCHAR(300)",
            nullable = false
    )
    private String observacion;

    @Column(
            name = "fecha_registro",
            nullable = false
    )
    private Date fechaRegistro;
    @PrePersist
    public void setFechaRegistro(){
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
    private Doctor idPaciente;
    //getters/setters/constructors


    public ObservacionPacienteDoctor() {
    }

    public ObservacionPacienteDoctor(int recordDoctor, String observacion, int estado, Doctor idPaciente) {
        this.recordDoctor = recordDoctor;
        this.observacion = observacion;
        this.estado = estado;
        this.idPaciente = idPaciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRecordDoctor() {
        return recordDoctor;
    }

    public void setRecordDoctor(int recordDoctor) {
        this.recordDoctor = recordDoctor;
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

    public Doctor getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Doctor idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public String toString() {
        return "ObservacionPacienteDoctor{" +
                "id=" + id +
                ", recordDoctor=" + recordDoctor +
                ", observacion='" + observacion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado=" + estado +
                ", idPaciente=" + idPaciente +
                '}';
    }
}
