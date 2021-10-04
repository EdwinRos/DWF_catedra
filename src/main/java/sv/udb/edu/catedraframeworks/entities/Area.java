package sv.udb.edu.catedraframeworks.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer areaId;

    @Column(
            name = "nombre_area",
            columnDefinition = "VARCHAR(50)",
            nullable = false
    )
    private String nombreArea;

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

    public Area() {
    }

    public Area(String nombreArea, Integer estado) {
        this.nombreArea = nombreArea;
        this.estado = estado;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
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
        return "Area{" +
                "areaId=" + areaId +
                ", nombreArea='" + nombreArea + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado=" + estado +
                '}';
    }
}
