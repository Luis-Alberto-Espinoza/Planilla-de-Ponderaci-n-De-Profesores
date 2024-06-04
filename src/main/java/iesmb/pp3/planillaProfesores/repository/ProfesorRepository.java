package iesmb.pp3.planillaProfesores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iesmb.pp3.planillaProfesores.entity.Profesor;

public interface ProfesorRepository extends JpaRepository <Profesor, Integer>{
public Profesor findByDocumento(String documento);
}
