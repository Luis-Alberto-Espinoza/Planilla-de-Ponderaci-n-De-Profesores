package iesmb.pp3.planillaProfesores.entity;

public class ActividadConPuntaje {
    private Actividad actividad;
    private PuntajeActividad puntajeActividad;

    public ActividadConPuntaje() {
    }

    public ActividadConPuntaje(Actividad actividad, PuntajeActividad puntajeActividad) {
        this.actividad = actividad;
        this.puntajeActividad = puntajeActividad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public PuntajeActividad getPuntajeActividad() {
        return puntajeActividad;
    }

    public void setPuntajeActividad(PuntajeActividad puntajeActividad) {
        this.puntajeActividad = puntajeActividad;
    }
}
