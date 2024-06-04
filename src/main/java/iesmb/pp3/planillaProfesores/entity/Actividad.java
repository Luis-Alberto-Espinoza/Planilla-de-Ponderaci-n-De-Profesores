package iesmb.pp3.planillaProfesores.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private double puntuacion;
    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "actividad")
    private List<PuntajeActividad> puntajesActividad;

    public Actividad() {
        puntajesActividad = new ArrayList<>();
    }

    public Actividad(String nombre, int puntuacion) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        puntajesActividad = new ArrayList<>();
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<PuntajeActividad> getPuntajesActividad() {
        return puntajesActividad;
    }

    public void setPuntajesActividad(List<PuntajeActividad> puntajesActividad) {
        this.puntajesActividad = puntajesActividad;
    }
}