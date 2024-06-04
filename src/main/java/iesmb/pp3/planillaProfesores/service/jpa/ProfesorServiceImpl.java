package iesmb.pp3.planillaProfesores.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iesmb.pp3.planillaProfesores.entity.Profesor;
import iesmb.pp3.planillaProfesores.repository.ProfesorRepository;
import iesmb.pp3.planillaProfesores.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService {

	@Autowired
	ProfesorRepository repo;

	@Override
	public List<Profesor>getAll() {
		
		List<Profesor> puntajeActividad = repo.findAll();
		return puntajeActividad;
	}

	@Override
	public Profesor getById(Integer id){
		
		Optional<Profesor> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Profesor getByDni(String dni) {
		Profesor profesor = repo.findByDocumento(dni);
		return profesor != null ? profesor : null;	}

	@Override
	public Profesor save(Profesor profesor){
		
		return repo.save(profesor);
	}

	@Override
	public void delete(Integer id){
		
		if(repo.existsById(id)) {
			repo.deleteById(id);
		}
	}
	
	@Override
	public boolean exists(Integer id) {
		
		return (id == null) ? false: repo.existsById(id);
	}
}
