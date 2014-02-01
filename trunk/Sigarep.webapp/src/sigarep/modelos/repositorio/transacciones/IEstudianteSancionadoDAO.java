package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;

public interface IEstudianteSancionadoDAO extends JpaRepository<EstudianteSancionado, EstudianteSancionadoPK> {
			
	@Query("Select esa FROM EstudianteSancionado AS esa where estatus = TRUE")		
	public List<EstudianteSancionado> buscarSancionadosActivos();
	
	@Query("Select esa FROM EstudianteSancionado AS esa, LapsoAcademico la " +
			" where la.estatus = TRUE " +
			"and la.codigoLapso = esa.id.codigoLapso and esa.id.cedulaEstudiante not in " +
			" (select ap.id.cedulaEstudiante from ApelacionEstadoApelacion " +
			"as ap where ap.id.idInstanciaApelada = '1')")		
	public List<EstudianteSancionado> buscarSancionados();
	
//Maria
	
	@Query("SELECT es FROM EstudianteSancionado AS es, LapsoAcademico AS la " +
			"WHERE (es.id.cedulaEstudiante NOT IN " +
			"(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa) " +
			"OR es.id.cedulaEstudiante NOT IN " +
			"(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa " +
			"WHERE sa.veredicto = 'PROCEDENTE')) " +
			"AND es.id.cedulaEstudiante NOT IN " +
			"(SELECT sa.id.cedulaEstudiante FROM SolicitudApelacion AS sa, LapsoAcademico AS la " +
			"WHERE sa.id.codigoLapso = la.codigoLapso AND la.estatus = 'TRUE' " +
			"AND sa.id.idInstanciaApelada = '3') " +
			"AND es.id.codigoLapso = la.codigoLapso " +
			"AND la.estatus = 'TRUE'")		
	public List<EstudianteSancionado> buscarSancionadosRecursoJerarquico();

}
