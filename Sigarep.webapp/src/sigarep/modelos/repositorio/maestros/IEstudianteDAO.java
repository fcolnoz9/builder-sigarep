package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Estudiante;

public interface IEstudianteDAO extends JpaRepository<Estudiante, String> {

}
