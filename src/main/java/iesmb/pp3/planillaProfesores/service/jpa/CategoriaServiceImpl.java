package iesmb.pp3.planillaProfesores.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iesmb.pp3.planillaProfesores.entity.Categoria;
import iesmb.pp3.planillaProfesores.repository.CategoriaRepository;
import iesmb.pp3.planillaProfesores.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	@Autowired
	CategoriaRepository repo;

	@Override
	public List<Categoria>getAll() {
		
		List<Categoria> categoria = repo.findAll();
		return categoria;
	}

	@Override
	public Categoria getById(Integer id){
		
		Optional<Categoria> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Categoria save(Categoria categoria){
		
		return repo.save(categoria);
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
