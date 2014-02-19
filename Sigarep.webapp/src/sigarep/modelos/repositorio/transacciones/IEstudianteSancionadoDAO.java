package sigarep.modelos.repositorio.transacciones;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;

public interface IEstudianteSancionadoDAO extends
		JpaRepository<EstudianteSancionado, EstudianteSancionadoPK> {

	@Query("Select esa FROM EstudianteSancionado AS esa where estatus = TRUE")
	public List<EstudianteSancionado> buscarSancionadosActivos();

	@Query("SELECT esa FROM EstudianteSancionado AS esa, LapsoAcademico AS la "
			+ "WHERE la.estatus = 'TRUE' "
			+ "AND la.codigoLapso = esa.id.codigoLapso "
			+ "AND esa.id.cedulaEstudiante NOT IN "
			+ "(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la "
			+ "WHERE la.codigoLapso = sa.id.codigoLapso "
			+ "AND la.estatus = 'TRUE')")
	public List<EstudianteSancionado> buscarSancionados();

	// Maria

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
			+ "AND sa.id.idInstanciaApelada = '3') "
			+ "AND es.id.codigoLapso = la.codigoLapso "
			+ "AND la.estatus = 'TRUE'")
	public List<EstudianteSancionado> buscarSancionadosRecursoJerarquico();

	// Vanessa
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

	@Query("select distinct sa from EstudianteSancionado sa "
			+ "  where sa.id.cedulaEstudiante = :cedula ")
	public List<EstudianteSancionado> buscarApelacion(
			@Param("cedula") String cedula);

	// Eliecer y Javier
	@Query("select distinct esa from EstudianteSancionado esa, LapsoAcademico AS la "
			+ "WHERE la.estatus = 'TRUE' "
			+ "AND la.codigoLapso = esa.id.codigoLapso "
			+ " AND esa.id.cedulaEstudiante = :cedula ")
	public EstudianteSancionado buscarSancionadoLapsoActual(
			@Param("cedula") String cedula);
}
