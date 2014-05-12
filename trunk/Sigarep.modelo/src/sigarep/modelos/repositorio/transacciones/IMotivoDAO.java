package sigarep.modelos.repositorio.transacciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;

/**
 * Repositorio IMotivoDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 12/05/2014
 */
public interface IMotivoDAO extends JpaRepository<Motivo, MotivoPK> {

	/**
	 * Busca las solicitudes de apelacion de los estudiantes sancionados
	 * @param solicitudApelacion
	 * @return List<Motivo> una lista con las solicitudes de apelacion 
	 */
	public List<Motivo> findBySolicitudApelacion(
			SolicitudApelacion solicitudApelacion);

	/**
	 * Busca las solicitudes de apelacion de los estudiantes sancionados en un lapso academico
	 * @param lapsoAcademico
	 * @return List<Motivo> una lista con las solicitudes de apelacion 
	 */
	public List<Motivo> findBySolicitudApelacion_EstudianteSancionado_LapsoAcademico(
			LapsoAcademico lapsoAcademico);

	/**
	 * Busca los motivos no generales de apelacion de una solicitud de un estudiante
	 * @param cedula
	 * @param codigoLapso
	 * @return List<String> una lista con los motivos de una solicitud
	 */
	@Query("Select distinct(tm.nombreTipoMotivo) FROM Motivo AS m, TipoMotivo AS tm WHERE m.id.cedulaEstudiante = :cedula AND m.id.codigoLapso = :codigoLapso "
			+ "and m.id.idTipoMotivo = tm.idTipoMotivo and m.id.idTipoMotivo <> 1 and m.id.idTipoMotivo <> 2 and m.id.idTipoMotivo <> 3")
	public List<String> buscarMotivosApelacion(@Param("cedula") String cedula,
			@Param("codigoLapso") String codigoLapso);
}
