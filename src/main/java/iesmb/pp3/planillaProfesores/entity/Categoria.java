package iesmb.pp3.planillaProfesores.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private int puntuacionMaxima;

    @OneToMany(mappedBy = "categoria")
    private List<Actividad> actividades;

    @OneToMany(mappedBy = "categoria")
    private List<PuntajeXCategoriaValidado> puntajesValidados;

    public Categoria() {
        actividades = new ArrayList<>();
        puntajesValidados = new ArrayList<>();
    }

    public Categoria(String nombre, int puntuacionMaxima) {
        this.nombre = nombre;
        this.puntuacionMaxima = puntuacionMaxima;
        actividades = new ArrayList<>();
        puntajesValidados = new ArrayList<>();
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

    public int getPuntuacionMaxima() {
        return puntuacionMaxima;
    }

    public void setPuntuacionMaxima(int puntuacionMaxima) {
        this.puntuacionMaxima = puntuacionMaxima;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<PuntajeXCategoriaValidado> getPuntajesValidados() {
        return puntajesValidados;
    }

    public void setPuntajesValidados(List<PuntajeXCategoriaValidado> puntajesValidados) {
        this.puntajesValidados = puntajesValidados;
    }
}