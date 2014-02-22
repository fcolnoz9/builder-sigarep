package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionadoPK;

public interface IAsignaturaEstudianteSancionadoDAO
		extends
		JpaRepository<AsignaturaEstudianteSancionado, AsignaturaEstudianteSancionadoPK> {

	/**
	 * Busca Busca las asignaturas por las que fue sancionado un estudiante en un lapso academico especifico
	 * @param cedulaEstudiante cedula del estudiante al que se le buscara las asignaturas
	 * @param codigoLapso lapso academico en el que fue sancionado el estudiante
	 * @return List<AsignaturaEstudianteSancionado>
	 */
	@Query("Select ae FROM AsignaturaEstudianteSancionado AS ae WHERE ae.id.cedulaEstudiante = :cedulaEstudiante AND ae.id.codigoLapso = :codigoLapso")
	public List<AsignaturaEstudianteSancionado> buscarAsignaturaDeSancionRR(@Param("cedulaEstudiante") String cedulaEstudiante, @Param("codigoLapso") String codigoLapso);
}
