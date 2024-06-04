package iesmb.pp3.planillaProfesores.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import iesmb.pp3.planillaProfesores.entity.Actividad;
import iesmb.pp3.planillaProfesores.entity.Categoria;
import iesmb.pp3.planillaProfesores.entity.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iesmb.pp3.planillaProfesores.entity.PuntajeActividad;
import iesmb.pp3.planillaProfesores.repository.PuntajeActividadRepository;
import iesmb.pp3.planillaProfesores.service.IPuntajeActividadService;

@Service
public class PuntajeActividadServiceImpl implements IPuntajeActividadService{

	@Autowired
	PuntajeActividadRepository puntajeActividadRepository;

	@Override
	public List<PuntajeActividad>getAll() {

		List<PuntajeActividad> puntajeActividad = puntajeActividadRepository.findAll();
		return puntajeActividad;
	}

	@Override
	public PuntajeActividad getById(Integer id){

		Optional<PuntajeActividad> optional = puntajeActividadRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public PuntajeActividad save(PuntajeActividad puntajeActividad){

		return puntajeActividadRepository.save(puntajeActividad);
	}

	@Override
	public boolean delete(Integer id){

		if(puntajeActividadRepository.existsById(id)) {
			puntajeActividadRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean exists(Integer id) {

		return (id == null) ? false: puntajeActividadRepository.existsById(id);
	}
	public List<PuntajeActividad> obtenerPuntajesPorProfesorYCategoria(Profesor profesor, Categoria categoria) {
		return puntajeActividadRepository.findByProfesorAndActividad_Categoria(profesor, categoria);
	}


	// Método para obtener o inicializar los puntajesActividad del profesor para una lista de actividades
	public List<PuntajeActividad> obtenerPuntajesActividad(Profesor profesor, List<Actividad> actividades) {
		List<PuntajeActividad> puntajesActividad = profesor.getPuntajesActividad();
		List<Actividad> actividadesFaltantes = new ArrayList<>(actividades);

		// Actualizar las actividades existentes
		for (PuntajeActividad puntajeActividad : puntajesActividad) {
			if (actividadesFaltantes.contains(puntajeActividad.getActividad())) {
				actividadesFaltantes.remove(puntajeActividad.getActividad());
			}
		}

		// Crear nuevos puntajes para las actividades faltantes
		for (Actividad actividad : actividadesFaltantes) {
			PuntajeActividad nuevoPuntaje = new PuntajeActividad();
			nuevoPuntaje.setPuntaje(0); // Otra lógica si es necesario
			nuevoPuntaje.setProfesor(profesor);
			nuevoPuntaje.setActividad(actividad);
			puntajesActividad.add(nuevoPuntaje);
		}

		return puntajesActividad;
	}



	// Método para obtener o inicializar los puntajesActividad del profesor para una lista de actividades
	public PuntajeActividad obtenerPuntajeActividad(List<PuntajeActividad> puntajesActividad, Actividad actividad) {
		for (PuntajeActividad puntajeActividad : puntajesActividad) {
			if (puntajeActividad.getActividad().equals(actividad)) {
				return puntajeActividad;
			}
		}
		// Si no se encuentra, se crea uno nuevo (esto no debería ocurrir en condiciones normales)
		PuntajeActividad nuevoPuntaje = new PuntajeActividad();
		nuevoPuntaje.setPuntaje(0); // Otra lógica si es necesario
		return nuevoPuntaje;
	}
	public int obtenerTotalPuntosPorProfesor(Profesor profesor) {
		List<PuntajeActividad> puntajesPorProfesor = puntajeActividadRepository.findByProfesor(profesor);

		int totalPuntos = 0;

		for (PuntajeActividad puntaje : puntajesPorProfesor) {
			totalPuntos += puntaje.getPuntaje();
		}

		return totalPuntos;
	}

}
