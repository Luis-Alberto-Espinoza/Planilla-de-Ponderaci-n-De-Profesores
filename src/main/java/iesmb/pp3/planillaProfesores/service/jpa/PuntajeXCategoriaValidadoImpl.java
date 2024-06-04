package iesmb.pp3.planillaProfesores.service.jpa;

import iesmb.pp3.planillaProfesores.entity.Categoria;
import iesmb.pp3.planillaProfesores.entity.Profesor;
import iesmb.pp3.planillaProfesores.entity.PuntajeXCategoriaValidado;
import iesmb.pp3.planillaProfesores.repository.PuntajeXCategoriaValidadoRepository;
import iesmb.pp3.planillaProfesores.service.IPuntajeXCategoriaValidadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class PuntajeXCategoriaValidadoImpl implements IPuntajeXCategoriaValidadoService {
    @Autowired
    PuntajeXCategoriaValidadoRepository puntajeXCategoriaValidadoRepository;

    @Override
    public List<PuntajeXCategoriaValidado> getAll() {
        List<PuntajeXCategoriaValidado> puntajeXCategoriaV = puntajeXCategoriaValidadoRepository.findAll();
        return puntajeXCategoriaV;
    }

    @Override
    public PuntajeXCategoriaValidado getById(Integer id) {
        Optional<PuntajeXCategoriaValidado> optional = puntajeXCategoriaValidadoRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public PuntajeXCategoriaValidado save(PuntajeXCategoriaValidado puntajeXCategoriaValidado) {
        return puntajeXCategoriaValidadoRepository.save(puntajeXCategoriaValidado);
    }

    @Override
    public boolean delete(Integer id) {
        if (puntajeXCategoriaValidadoRepository.existsById(id)) {
            puntajeXCategoriaValidadoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public double obtenerTotalPuntosPorProfesor(Profesor profesor) {
        List<PuntajeXCategoriaValidado> puntajesValidados = puntajeXCategoriaValidadoRepository.getByProfesor(profesor);

        double totalPuntos = 0.0;

        for (PuntajeXCategoriaValidado puntajeValidado : puntajesValidados) {
            totalPuntos += puntajeValidado.getPuntajeValidado();
        }

        return totalPuntos;    }


    @Override
    public PuntajeXCategoriaValidado getByProfesorAndCategoria(Profesor profesor, Categoria categoria) {
        return puntajeXCategoriaValidadoRepository.findByProfesorAndCategoria(profesor, categoria);
    }

    @Override
    public boolean exists(Integer id) {
        return (id == null) ? false: puntajeXCategoriaValidadoRepository.existsById(id);
    }
}
