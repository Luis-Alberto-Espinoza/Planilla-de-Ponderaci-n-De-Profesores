package iesmb.pp3.planillaProfesores.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    @OneToMany(mappedBy = "profesor")
    private List<PuntajeActividad> puntajesActividad;

    @OneToMany(mappedBy = "profesor")
    private List<PuntajeXCategoriaValidado> puntajesValidados;

    public Profesor() {
        puntajesActividad = new ArrayList<>();
        puntajesValidados = new ArrayList<>();
    }

    public Profesor(String documento, String nombre, String apellido, String telefono, String direccion) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        puntajesActividad = new ArrayList<>();
        puntajesValidados = new ArrayList<>();
    }


    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<PuntajeActividad> getPuntajesActividad() {
        return puntajesActividad;
    }

    public void setPuntajesActividad(List<PuntajeActividad> puntajesActividad) {
        this.puntajesActividad = puntajesActividad;
    }

    public void addPuntajeActividad(PuntajeActividad puntajeActividad) {
        puntajesActividad.add(puntajeActividad);
        puntajeActividad.setProfesor(this);
    }

    public void removePuntajeActividad(PuntajeActividad puntajeActividad) {
        puntajesActividad.remove(puntajeActividad);
        puntajeActividad.setProfesor(null);
    }


    public List<PuntajeXCategoriaValidado> getPuntajesValidados() {
        return puntajesValidados;
    }

    public void setPuntajesValidados(List<PuntajeXCategoriaValidado> puntajesValidados) {
        this.puntajesValidados = puntajesValidados;
    }
}
