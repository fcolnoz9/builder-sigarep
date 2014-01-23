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
	
	@Query("Select DISTINCT esa FROM EstudianteSancionado AS esa, LapsoAcademico la, InstanciaApelada i, " +
			"SolicitudApelacion sap, ApelacionEstadoApelacion ap where la.estatus = 'TRUE' and " +
			"ap.id.idInstanciaApelada  = '1' and sap.veredicto <> '' and esa.id.cedulaEstudiante = " +
			"sap.id.cedulaEstudiante and i.idInstanciaApelada = ap.id.idInstanciaApelada and " +
			" la.codigoLapso = esa.id.codigoLapso " +
			"and sap.id.cedulaEstudiante not in (select ap.id.cedulaEstudiante from ApelacionEstadoApelacion " +
			"as ap where ap.id.idInstanciaApelada = '2') ")		
	public List<EstudianteSancionado> buscarSancionadosReconsideracion();
}
