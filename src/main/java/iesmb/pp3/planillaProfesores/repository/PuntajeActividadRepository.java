package iesmb.pp3.planillaProfesores.repository;

import iesmb.pp3.planillaProfesores.entity.Categoria;
import iesmb.pp3.planillaProfesores.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import iesmb.pp3.planillaProfesores.entity.PuntajeActividad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PuntajeActividadRepository extends JpaRepository <PuntajeActividad, Integer>{
    List<PuntajeActividad> findByProfesorId(Integer profesorId);

    List<PuntajeActividad> findByProfesorAndActividad_Categoria(Profesor profesor, Categoria categoria);

    /*
    Esta consulta busca todos los puntajes de actividad relacionados con un profesor específico.
     */
    List<PuntajeActividad> findByProfesor(Profesor profesor);

//    @Query("SELECT c FROM PuntajeActividad el resto de la consulta + el order by categoria ")
//    public PuntajeActividad buscarTodasLosPuntajesAtividadOrdenadosPorCategoria(@Param("profesor") Profesor profesor);
}
