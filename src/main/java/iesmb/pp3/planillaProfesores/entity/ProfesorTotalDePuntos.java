package iesmb.pp3.planillaProfesores.entity;

public class ProfesorTotalDePuntos {
    private Profesor profesor;
    private double totalAcumulado;

    public ProfesorTotalDePuntos() {
    }

    public ProfesorTotalDePuntos(Profesor profesor, double totalAcumulado) {
        this.profesor = profesor;
        this.totalAcumulado = totalAcumulado;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public double getTotalAcumulado() {
        return totalAcumulado;
    }

    public void setTotalAcumulado(double totalAcumulado) {
        this.totalAcumulado = totalAcumulado;
    }
}
