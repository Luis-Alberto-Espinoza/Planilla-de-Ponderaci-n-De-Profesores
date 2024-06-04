package iesmb.pp3.planillaProfesores.service;

import iesmb.pp3.planillaProfesores.entity.Categoria;
import iesmb.pp3.planillaProfesores.entity.Profesor;
import iesmb.pp3.planillaProfesores.entity.PuntajeXCategoriaValidado;

import java.util.List;

public interface IPuntajeXCategoriaValidadoService {


    public List<PuntajeXCategoriaValidado> getAll();

    public PuntajeXCategoriaValidado getById(Integer id);

    public PuntajeXCategoriaValidado save(PuntajeXCategoriaValidado puntajeXCategoriaValidado);

    public boolean delete(Integer id);

    double obtenerTotalPuntosPorProfesor(Profesor profesor);

    public PuntajeXCategoriaValidado getByProfesorAndCategoria(Profesor profesor, Categoria categoria);

    boolean exists(Integer id);
    /*
    *   PuntajeXCategoriaValidado getByProfesorAndCategoria(Profesor profesor, Categoria categoria);

    * */

}

