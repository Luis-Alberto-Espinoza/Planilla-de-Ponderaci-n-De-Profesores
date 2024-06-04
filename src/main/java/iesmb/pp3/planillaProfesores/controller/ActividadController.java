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
import iesmb.pp3.planillaProfesores.entity.Actividad;
import iesmb.pp3.planillaProfesores.service.IActividadService;
import iesmb.pp3.planillaProfesores.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/actividades")
public class ActividadController {

    @Autowired
    IActividadService service;

    @GetMapping("")
	public ResponseEntity<APIResponse<List<Actividad>>>getAllActividades() {

		return service.getAll().isEmpty() ? ResponseUtil.notFound("No hay actividades creadas, por favor, cree algunas a trvés del método POST.")
			      : ResponseUtil.success(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Actividad>> getActividadById(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.getById(id))
				 : ResponseUtil.notFound("Actividad no encontrada para ese id.");
	}

	@PostMapping("")
	public ResponseEntity<APIResponse<Actividad>> createActividad(@RequestBody Actividad actividad) {

		return service.exists(actividad.getId()) ? ResponseUtil.badRequest("Ya hay una actividad creada con ese id. Para actualizar, utilice el verbo PUT.")
				 : ResponseUtil.created(service.save(actividad));
	}

	@PutMapping("")
	public ResponseEntity<APIResponse<Actividad>> editActividad(@RequestBody Actividad actividad) {
		return service.exists(actividad.getId()) ? ResponseUtil.success(service.save(actividad))
				 : ResponseUtil.badRequest("No existe una actividad con ese id. Para crearla, utilice el método POST.");
		}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Boolean>> deleteActividad(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.delete(service.getById(id).getId()))
				 : ResponseUtil.notFound("Actividad no encontrada para ese id.");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Actividad>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Actividad>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}
