package sigarep.modelos.repositorio.transacciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;

/**
 * Repositorio IEstudianteSancionadoDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public interface IEstudianteSancionadoDAO extends
		JpaRepository<EstudianteSancionado, EstudianteSancionadoPK> {

	/**
	 * Busca en el lapso activo los estudiantes que han sido sancionados
	 * @return List<EstudianteSancionado> lista con estudiantes sancionados 
	 */
	@Query("Select esa FROM EstudianteSancionado AS esa, LapsoAcademico AS la WHERE esa.estatus = 'TRUE' "
			+ "AND esa.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE'")
	public List<EstudianteSancionado> buscarSancionadosActivos();
	
	/**
	 * busca estudiantes en la tabla estudiantes
	 * @return List<EstudianteSancionado> lista con estudiantes
	 */
	@Query("Select esa FROM EstudianteSancionado AS esa")
	public List<EstudianteSancionado> buscarEstudiante();

	/**
	 * Busca los sancionados que no hayan echo una apelacion
	 * @return List<EstudianteSancionado> lista con estudiantes sancionados sin apelacion
	 */
	@Query("SELECT esa FROM EstudianteSancionado AS esa, LapsoAcademico AS la  "
			+ "WHERE la.estatus = 'TRUE' "
			+ "AND la.codigoLapso = esa.id.codigoLapso "
			+ "AND esa.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE la.codigoLapso = sa.id.codigoLapso "
			+ "AND la.estatus = 'TRUE' and sa.id.idInstanciaApelada = '1') " )
	public List<EstudianteSancionado> buscarSancionados();

	/**
	 * Busca los sancionados cuyas solicitudes no hayan sido procedentes ante el concejo de decanato
	 * para apelar ante el concejo universitario
	 * @return List<EstudianteSancionado> lista con estudiantes sancionados
	 */
	@Query("SELECT es FROM EstudianteSancionado AS es, LapsoAcademico AS la "
			+ "WHERE es.id.cedulaEstudiante IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.veredicto = 'NO PROCEDENTE' "
			+ "AND sa.id.idInstanciaApelada = '2' "
			+ "AND sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE' "
			+ "AND sa.numeroSesion IS NOT NULL) "
			+ "AND es.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE' "
			+ "AND sa.id.idInstanciaApelada = '3') "
			+ "AND es.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE' ")
	public List<EstudianteSancionado> buscarSancionadosRecursoJerarquicoParte1();
	
	/**
	 * Busca los sancionados cuyas solicitudes no hayan sido procedentes ante el concejo de decanato
	 * para apelar ante el concejo universitario
	 * @return List<EstudianteSancionado> lista con estudiantes sancionados
	 */
	@Query("SELECT es FROM EstudianteSancionado AS es, LapsoAcademico AS la "
			+ "WHERE ((es.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.idInstanciaApelada = '2' "
			+ "AND sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE') "
			+ ""
			+ "AND es.id.cedulaEstudiante IN (SELECT sa.id.cedulaEstudiante "
			+ "FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.idInstanciaApelada = '1' "
			+ "AND sa.veredicto = 'NO PROCEDENTE' "
			+ "AND sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE') "
			+ ""
			+ "AND es.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE' "
			+ "AND sa.id.idInstanciaApelada = '3')) "
			+ ""
			+ "OR es.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE')) "
			+ ""
			+ "AND es.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE'")
	public List<EstudianteSancionado> buscarSancionadosRecursoJerarquicoParte2();

	/**
	 * Busca los sancionados que no les hayan aprobado la solicitud en la primera apelacion
	 * @return List<EstudianteSancionado> lista con estudiantes sancionados
	 */
	@Query("SELECT es FROM EstudianteSancionado AS es, LapsoAcademico AS la "
			+ "WHERE (es.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE') "
			+ "OR es.id.cedulaEstudiante IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.veredicto = 'NO PROCEDENTE' "
			+ "AND sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE')) "
			+ "AND es.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE' "
			+ "AND (sa.id.idInstanciaApelada = '3' "
			+ "OR sa.id.idInstanciaApelada = '2')) "
			+ "AND es.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE'")
	public List<EstudianteSancionado> buscarSancionadosReconsideracion();

	/**
	 * Busca todas las apelaciones de un estudiante
	 * @param cedula
	 * @return List<EstudianteSancionado> lista con las apelaciones
	 */
	@Query("select distinct sa from EstudianteSancionado sa "
			+ "  where sa.id.cedulaEstudiante = :cedula ")
	public List<EstudianteSancionado> buscarApelacion(
			@Param("cedula") String cedula);

	/**
	 * Busca a un estudiante que haya sido sancionado en el lapso actual
	 * @param cedula
	 * @return Estudiante sancionado
	 */
	@Query("select distinct esa from EstudianteSancionado esa, LapsoAcademico AS la "
			+ "WHERE la.estatus = 'TRUE' "
			+ "AND la.codigoLapso = esa.id.codigoLapso "
			+ " AND esa.id.cedulaEstudiante = :cedula ")
	public EstudianteSancionado buscarSancionadoLapsoActual(
			@Param("cedula") String cedula);
	
	/**
	 * Busca estudiantes sancionados por lapso
	 * @param lapsoAcademico
	 * @return List<EstudianteSancionado> lista de estudiantes sancionados
	 */
	public List<EstudianteSancionado> findByLapsoAcademico(LapsoAcademico lapsoAcademico);

	/**
	 * Busca estudiantes que hayan entregado recaudos
	 * @return List<EstudianteSancionado> lista con estudiantes sancionados
	 */
	@Query("SELECT es FROM EstudianteSancionado AS es, LapsoAcademico AS la "
			+ "WHERE es.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE' "
			+ "AND es.id.cedulaEstudiante IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE sa.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus ='TRUE')")
	public List<EstudianteSancionado> buscarEstudiantesCargarRecaudoEntregado();
}
