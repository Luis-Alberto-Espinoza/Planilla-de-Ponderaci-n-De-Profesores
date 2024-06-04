package iesmb.pp3.planillaProfesores.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iesmb.pp3.planillaProfesores.entity.Actividad;
import iesmb.pp3.planillaProfesores.repository.ActividadRepository;
import iesmb.pp3.planillaProfesores.service.IActividadService;

@Service
public class ActividadServiceImpl implements IActividadService{
	@Autowired
	ActividadRepository repo;

	@Override
	public List<Actividad>getAll() {
		
		List<Actividad> actividad = repo.findAll();
		return actividad;
	}

	@Override
	public Actividad getById(Integer id){
		
		Optional<Actividad> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Actividad save(Actividad actividad){
		
		return repo.save(actividad);
	}

	@Override
	public boolean delete(Integer id){
		
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean exists(Integer id) {
		
		return (id == null) ? false: repo.existsById(id);
	}
}
