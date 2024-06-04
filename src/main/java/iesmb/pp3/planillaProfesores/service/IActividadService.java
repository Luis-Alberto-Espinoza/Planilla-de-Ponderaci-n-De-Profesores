package iesmb.pp3.planillaProfesores.service;

import java.util.List;

import iesmb.pp3.planillaProfesores.entity.Actividad;

public interface IActividadService {

	public List<Actividad> getAll();

	public Actividad getById (Integer id);

	public Actividad save (Actividad actividad);

	public boolean delete (Integer id);
	
	boolean exists(Integer id);
}
