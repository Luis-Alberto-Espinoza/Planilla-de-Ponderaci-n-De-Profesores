package iesmb.pp3.planillaProfesores.service;

import java.util.List;

import iesmb.pp3.planillaProfesores.entity.PuntajeActividad;

public interface IPuntajeActividadService {

	public List<PuntajeActividad> getAll();

	public PuntajeActividad getById (Integer id);

	public PuntajeActividad save (PuntajeActividad puntajeActividad);

	public boolean delete (Integer id);

	boolean exists(Integer id);
}
