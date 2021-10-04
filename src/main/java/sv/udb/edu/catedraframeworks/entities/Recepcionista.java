package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "recepcionista")
public class Recepcionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recepcionistaId;

    @Column(
            name = "nombre_repecionista",
            nullable = false,
            columnDefinition = "VARCHAR(80)"
    )
    private String nombreRecepcionista;

    @Column(
            name = "apellido_recepcionista",
            nullable = false,
            columnDefinition = "VARCHAR(80)"
    )
    private String apellidoRecepcionista;

    @Column(
            name = "usuario",
            nullable = false,
            columnDefinition = "VARCHAR(80)",
            unique = true
    )
    private String usuario;

    @Column(
            name = "password",
            columnDefinition = "VARCHAR(250)",
            nullable = false
    )
    private String password;

    @Column(
            name = "estado",
            nullable = false
    )
    private Integer estado;

    @Column(
            name = "fecha_registro",
            updatable = false,
            nullable = false
    )
    private Date fechaRegistro;

    @PrePersist
    protected  void setFechaRegistro(){
        this.fechaRegistro = new Date();
    }

    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "area_id",
            nullable = false
    )
    private Area areaId;
   //metodos get/set and constructor


    public Recepcionista() {
    }

    public Recepcionista(String nombreRecepcionista, String apellidoRecepcionista, String usuario, String password, Integer estado, Area areaId) {
        this.nombreRecepcionista = nombreRecepcionista;
        this.apellidoRecepcionista = apellidoRecepcionista;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
        this.areaId = areaId;
    }

    public Integer getRecepcionistaId() {
        return recepcionistaId;
    }

    public void setRecepcionistaId(Integer recepcionistaId) {
        this.recepcionistaId = recepcionistaId;
    }

    public String getNombreRecepcionista() {
        return nombreRecepcionista;
    }

    public void setNombreRecepcionista(String nombreRecepcionista) {
        this.nombreRecepcionista = nombreRecepcionista;
    }

    public String getApellidoRecepcionista() {
        return apellidoRecepcionista;
    }

    public void setApellidoRecepcionista(String apellidoRecepcionista) {
        this.apellidoRecepcionista = apellidoRecepcionista;
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

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }
}
