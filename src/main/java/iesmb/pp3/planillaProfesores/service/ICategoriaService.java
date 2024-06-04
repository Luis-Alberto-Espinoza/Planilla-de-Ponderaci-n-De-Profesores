package iesmb.pp3.planillaProfesores.service;

import java.util.List;

import iesmb.pp3.planillaProfesores.entity.Categoria;


public interface ICategoriaService {

	public List<Categoria> getAll();

	public Categoria getById (Integer id);

	public Categoria save (Categoria categoria);

	public boolean delete (Integer id);
	
	boolean exists(Integer id);
}
