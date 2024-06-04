package iesmb.pp3.planillaProfesores.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iesmb.pp3.planillaProfesores.entity.PuntajeActividad;
import iesmb.pp3.planillaProfesores.service.IPuntajeActividadService;
import iesmb.pp3.planillaProfesores.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/puntajesactividad")
public class PuntajeActividadController {

	@Autowired
	IPuntajeActividadService service;

	@GetMapping("")
	public ResponseEntity<APIResponse<List<PuntajeActividad>>>getAllPuntajesActividad() {

		return service.getAll().isEmpty() ? ResponseUtil.notFound("No hay puntajes creados, por favor, cree algunos a trvés del método POST.")
			      : ResponseUtil.success(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<PuntajeActividad>> getPuntajeActividadById(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.getById(id))
				 : ResponseUtil.notFound("Puntaje no encontrado para ese id.");
	}

	@PostMapping("")
	public ResponseEntity<APIResponse<PuntajeActividad>> createPuntajeActividad(@RequestBody PuntajeActividad puntajeActividad) {

		return service.exists(puntajeActividad.getId()) ? ResponseUtil.badRequest("Ya hay un profesor creado con ese id. Para actualizar, utilice el verbo PUT.")
				 : ResponseUtil.created(service.save(puntajeActividad));
	}

	@PutMapping("")
	public ResponseEntity<APIResponse<PuntajeActividad>> editPuntajeActividad(@RequestBody PuntajeActividad puntajeActividad) {
		return service.exists(puntajeActividad.getId()) ? ResponseUtil.success(service.save(puntajeActividad))
				 : ResponseUtil.badRequest("No existe un puntaje con ese id. Para crearlo, utilice el método POST.");
		}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Boolean>> deletePuntajeActividad(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.delete(service.getById(id).getId()))
				 : ResponseUtil.notFound("Puntaje no encontrado para ese id.");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<PuntajeActividad>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<PuntajeActividad>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}