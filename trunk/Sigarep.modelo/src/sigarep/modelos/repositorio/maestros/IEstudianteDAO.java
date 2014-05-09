package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Estudiante;

/**
 * Repositorio IEstudianteDAO: Repositorio relacionado con el Maestro Estudiante. 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface IEstudianteDAO extends JpaRepository<Estudiante, String> {

	/**
	 * Busca estudiantes en la tabla Estudiante
	 * 
	 * @return List<Estudiante> Lista de  Estudiantes 
	 */
	@Query("Select es FROM Estudiante AS es")
	public List<Estudiante> buscarEstudiante();
}
