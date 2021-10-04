package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "cita")
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citasId;

    @Column(
            name = "fecha_cita",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date fechaCita;

    @Column(
            name = "hora_cita",
            nullable = false,
            updatable = false,
            columnDefinition = "TIME"
    )
    private LocalTime horaCita;

    @Column(
            name = "extras",
            columnDefinition = "VARCHAR(100)"
    )
    private String extras;

    @ManyToOne
    @JoinColumn(
            name = "id_doctor",
            nullable = false
    )
    private Doctor idDoctor;

    @ManyToOne
    @JoinColumn(
            name = "id_paciente",
            nullable = false
    )
    private Paciente idPaciente;
   //getters/setters/constructors


    public Citas() {
    }

    public Citas(Date fechaCita, LocalTime horaCita, String extras, Doctor idDoctor, Paciente idPaciente) {
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.extras = extras;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
    }

    public Integer getCitasId() {
        return citasId;
    }

    public void setCitasId(Integer citasId) {
        this.citasId = citasId;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }



    @Override
    public String toString() {
        return "Citas{" +
                "citasId=" + citasId +
                ", fechaCita=" + fechaCita +
                ", horaCita=" + horaCita +
                ", extras='" + extras + '\'' +
                ", idDoctor=" + idDoctor +
                ", idPaciente=" + idPaciente +
                '}';
    }
}
