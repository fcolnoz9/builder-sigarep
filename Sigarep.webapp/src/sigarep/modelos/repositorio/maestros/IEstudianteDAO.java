package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

public interface IEstudianteDAO extends JpaRepository<Estudiante, String> {

	@Query("Select es FROM Estudiante AS es")
	public List<Estudiante> buscarEstudiante();
}
