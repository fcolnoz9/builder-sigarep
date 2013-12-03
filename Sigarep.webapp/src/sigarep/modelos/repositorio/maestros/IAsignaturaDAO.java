package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Asignatura;

public interface IAsignaturaDAO extends JpaRepository<Asignatura, String> {

}
