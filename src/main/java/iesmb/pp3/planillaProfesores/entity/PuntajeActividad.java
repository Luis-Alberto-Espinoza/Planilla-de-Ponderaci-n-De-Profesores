package iesmb.pp3.planillaProfesores.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PuntajeActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Profesor profesor;

    @ManyToOne
    private Actividad actividad;

    private float puntaje;

    public PuntajeActividad() {
    	
    }

    public PuntajeActividad(Profesor profesor, Actividad actividad, float puntaje) {
        this.profesor = profesor;
        this.actividad = actividad;
        this.puntaje = puntaje;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(float puntaje) {
        this.puntaje = puntaje;
    }
}
