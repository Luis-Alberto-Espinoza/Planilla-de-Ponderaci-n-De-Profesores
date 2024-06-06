package iesmb.pp3.planillaProfesores.entity;

import jakarta.persistence.*;

@Entity
public class PuntajeXCategoriaValidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float puntajeValidado;

    @ManyToOne
    private Profesor profesor;

    @ManyToOne
    private Categoria categoria;

    public PuntajeXCategoriaValidado() {
    }

    public PuntajeXCategoriaValidado(Integer id, float puntajeValidado, Profesor profesor, Categoria categoria) {
        this.id = id;
        this.puntajeValidado = puntajeValidado;
        this.profesor = profesor;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPuntajeValidado() {
        return puntajeValidado;
    }

    public void setPuntajeValidado(float puntajeValidado) {
        this.puntajeValidado = puntajeValidado;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
