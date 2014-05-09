package sigarep.modelos.repositorio.transacciones;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionadoPK;

/**
 * Repositorio IAsignaturaEstudianteSancionadoDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public interface IAsignaturaEstudianteSancionadoDAO
		extends
		JpaRepository<AsignaturaEstudianteSancionado, AsignaturaEstudianteSancionadoPK> {

	/**
	 * Busca Busca las asignaturas por las que fue sancionado un estudiante en un lapso academico especifico
	 * @param cedulaEstudiante 
	 * @param codigoLapso 
	 * @return List<AsignaturaEstudianteSancionado> lista con las asignaturas
	 */
	public List<AsignaturaEstudianteSancionado> findById_CedulaEstudianteAndId_CodigoLapso(String cedulaEstudiante, String codigoLapso);
}
