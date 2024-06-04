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
import iesmb.pp3.planillaProfesores.entity.Categoria;
import iesmb.pp3.planillaProfesores.service.ICategoriaService;
import iesmb.pp3.planillaProfesores.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	ICategoriaService service;

	@GetMapping("")
	public ResponseEntity<APIResponse<List<Categoria>>>getAllCategorias() {

		return service.getAll().isEmpty() ? ResponseUtil.notFound("No hay categorías creadas, por favor, cree algunas a trvés del método POST.")
			      : ResponseUtil.success(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> getCategoriaById(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.getById(id))
				 : ResponseUtil.notFound("Categoria no encontrada para ese id.");
	}

	@PostMapping("")
	public ResponseEntity<APIResponse<Categoria>> createCategoria(@RequestBody Categoria categoria) {

		return service.exists(categoria.getId()) ? ResponseUtil.badRequest("Ya hay una categoria creada con ese id. Para actualizar, utilice el verbo PUT.")
				 : ResponseUtil.created(service.save(categoria));
	}

	@PutMapping("")
	public ResponseEntity<APIResponse<Categoria>> editCategoria(@RequestBody Categoria categoria) {
		return service.exists(categoria.getId()) ? ResponseUtil.success(service.save(categoria))
				 : ResponseUtil.badRequest("No existe una actividad con ese id. Para crearla, utilice el método POST.");
		}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Boolean>> deleteCategoria(@PathVariable("id") Integer id){
		return service.exists(id) ? ResponseUtil.success(service.delete(service.getById(id).getId()))
				 : ResponseUtil.notFound("Categoría no encontrada para ese id.");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Categoria>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Categoria>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}
