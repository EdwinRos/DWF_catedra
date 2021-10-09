package sv.udb.edu.catedraframeworks.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "doctor",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "usuario_doctor_unico",
                        columnNames = "usuario"
                ),
                @UniqueConstraint(
                        name = "dui_doctor_unico",
                        columnNames = "dui"
                ),
                @UniqueConstraint(
                        name = "correo_doctor_unico",
                        columnNames = "correo"
                )
        }
)
public class Doctor {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer doctorId;

    @Column(
            name = "nombre",
            nullable = false,
            columnDefinition = "VARCHAR(70)"
    )
    private String nombreDoctor;

    @Column(
            name = "apellido",
            nullable = false,
            columnDefinition = "VARCHAR(70)"
    )
    private String apellidoDoctor;

    @Column(
            name = "fecha_nacimiento",
            nullable = false
    )
    private Date fechaNacimiento;

    @Column(
            name = "usuario",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String usuario;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "VARCHAR(250)"
    )
    private String password ;

    @Column(
            name = "dui",
            nullable = false,
            columnDefinition = "VARCHAR(10)"
    )
    private String duiDoctor;

    @Column(
            name = "correo",
            nullable = false,
            columnDefinition = "VARCHAR(70)"
    )
    private String correoDoctor;

    @Column(
            name = "estado",
            nullable = false
    )
    private Integer estado;

    @ManyToOne
    @JoinColumn(
            name = "id_area"
            
    )
    private Area idArea;

    @Column(
            name = "fechaRegistro",
            nullable = false
    )
    private Date fechaRegistro;
    @PrePersist
    protected  void setFechaRegistro (){
        this.fechaRegistro =  new Date();
    }
//    getters/setters/ constructors


    public Doctor() {
    }

    public Doctor(String nombreDoctor, String apellidoDoctor, Date fechaNacimiento, String usuario, String password, String duiDoctor, String correoDoctor, Integer estado, Area idArea) {
        this.nombreDoctor = nombreDoctor;
        this.apellidoDoctor = apellidoDoctor;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.password = password;
        this.duiDoctor = duiDoctor;
        this.correoDoctor = correoDoctor;
        this.estado = estado;
        this.idArea = idArea;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getApellidoDoctor() {
        return apellidoDoctor;
    }

    public void setApellidoDoctor(String apellidoDoctor) {
        this.apellidoDoctor = apellidoDoctor;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getDuiDoctor() {
        return duiDoctor;
    }

    public void setDuiDoctor(String duiDoctor) {
        this.duiDoctor = duiDoctor;
    }

    public String getCorreoDoctor() {
        return correoDoctor;
    }

    public void setCorreoDoctor(String correoDoctor) {
        this.correoDoctor = correoDoctor;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
