package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "paciente",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "usuario_paciente_unico",
                        columnNames = "usuario"
                ),
                @UniqueConstraint(
                        name = "correo_paciente_unico",
                        columnNames = "correo_paciente"
                ),
                @UniqueConstraint(
                        name = "dui_paciente_unico",
                        columnNames = "dui_paciente"
                )
        }
)
public class Paciente {
    @Id
//    @SequenceGenerator(
//            name = "paciente_sequence",
//            sequenceName = "paciente_sequence",
//            initialValue = 1
//    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
//            generator = "paciente_sequence"
    )
    private Long pacienteId;

    @Column(
            name = "nombre_paciente",
            nullable = false,
            columnDefinition = "VARCHAR(70)"
    )
    private String nombrePaciente;

    @Column(
            name = "apellido_paciente",
            nullable = false,
            columnDefinition = "VARCHAR(70)"
    )
    private String apellidoPaciente;

    @Column(
            name = "usuario",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String usuario;

    @Column(
            name = "password",
            columnDefinition = "VARCHAR(250)",
            nullable = false
    )
    private String password;

    @Column(
            name = "telefono",
            nullable = false,
            columnDefinition = "VARCHAR(15)"
    )
    private String telefono;

    @Column(
            name = "correo_paciente",
            nullable = false,
            columnDefinition = "VARCHAR(100)"
    )
    private String correoPaciente;

    @Column(
            name = "dui_paciente",
            nullable = false,
            columnDefinition = "VARCHAR(10)"
    )
    private String duiPaciente;

    @Column(
            name = "fecha_nacimiento",
            nullable = false
    )
    private Date fechaNacimiento;

    @Column(
            name = "fecha_registro",
            nullable = false
    )
    private Date fechaRegistro;

    @PrePersist
    protected  void setFechaRegistro(){
        this.fechaRegistro = new Date();
    }

    @Column(
            name = "estado",
            nullable = false
    )
    private Integer estado;
//    getters and setters methods /  constructors


    public Paciente() {
    }

    public Paciente(String nombrePaciente, String apellidoPaciente, String usuario, String password, String telefono, String correoPaciente, String duiPaciente, Date fechaNacimiento, Integer estado) {
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.usuario = usuario;
        this.password = password;
        this.telefono = telefono;
        this.correoPaciente = correoPaciente;
        this.duiPaciente = duiPaciente;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoPaciente() {
        return correoPaciente;
    }

    public void setCorreoPaciente(String correoPaciente) {
        this.correoPaciente = correoPaciente;
    }

    public String getDuiPaciente() {
        return duiPaciente;
    }

    public void setDuiPaciente(String duiPaciente) {
        this.duiPaciente = duiPaciente;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
        return "Paciente{" +
                "pacienteId=" + pacienteId +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", apellidoPaciente='" + apellidoPaciente + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoPaciente='" + correoPaciente + '\'' +
                ", duiPaciente='" + duiPaciente + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaRegistro=" + fechaRegistro +
                ", estado=" + estado +
                '}';
    }
}
