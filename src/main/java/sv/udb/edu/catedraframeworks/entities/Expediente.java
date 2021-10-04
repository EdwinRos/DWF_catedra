package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expediente")
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expedienteId;

    @Column(
            name = "registro",
            nullable = false,
            columnDefinition = "VARCHAR(150)"
    )
    private String registros;

    @Column(
            name = "fecha_registro",
            nullable = false
    )
    private Date fechaRegistro;
    @PrePersist
    protected void setFechaRegistro(){
        this.fechaRegistro = new Date();
    }

    @ManyToOne
    @JoinColumn(
        name = "paciente_id",
        nullable = false
    )
    private Paciente pacienteId;
//   getters/ setters/ constructors


    public Expediente() {
    }

    public Expediente(String registros, Paciente pacienteId) {
        this.registros = registros;
        this.pacienteId = pacienteId;
    }

    public Integer getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(Integer expedienteId) {
        this.expedienteId = expedienteId;
    }

    public String getRegistros() {
        return registros;
    }

    public void setRegistros(String registros) {
        this.registros = registros;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Paciente getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Paciente pacienteId) {
        this.pacienteId = pacienteId;
    }

    @Override
    public String toString() {
        return "Expediente{" +
                "expedienteId=" + expedienteId +
                ", registros='" + registros + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", pacienteId=" + pacienteId +
                '}';
    }
}
