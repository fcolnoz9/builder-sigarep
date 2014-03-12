package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Estudiante;

/**
 * Repositorio Estudiante-IEstudianteDAO 
 * 
 * @author BUILDER
 * @version 1.0
 * @since 12/12/2013
 */

public interface IEstudianteDAO extends JpaRepository<Estudiante, String> {

	/**
	 * Buscar Estudiante
	 * 
	 * @return Lista de  Estudiantes
	 */
	@Query("Select es FROM Estudiante AS es")
	public List<Estudiante> buscarEstudiante();
}
