package sigarep.modelos.repositorio.transacciones;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.data.transacciones.*;
import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

/**
 * Repositorio IApelacionEstadoApelacionDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 09/05/2014
 */
public interface IApelacionEstadoApelacionDAO extends
		JpaRepository<ApelacionEstadoApelacion, ApelacionEstadoApelacionPK> {
	/**
	 * Busca las apelaciones en lapsos anteriores
	 * @param cedula
	 * @param codigoLapso
	 * @param instancia
	 * @return List<ApelacionEstadoApelacion> buscarApelacionHistorial lista con las apelaciones
	 */
	@Query("select sa from ApelacionEstadoApelacion sa, SolicitudApelacion sap where sa.id.cedulaEstudiante = :cedula "
			+ " and sap.id.codigoLapso = sa.id.codigoLapso and sap.id.cedulaEstudiante = sa.id.cedulaEstudiante and sa.id.codigoLapso = :codigoLapso "
			+ "and sap.id.idInstanciaApelada = sa.id.idInstanciaApelada and sa.id.idInstanciaApelada =:instancia order by sa.id.idEstadoApelacion asc ")
	public List<ApelacionEstadoApelacion> buscarApelacionHistorial(
			@Param("cedula") String cedula,
			@Param("codigoLapso") String codigoLapso,
			@Param("instancia") Integer instancia);

	/**
	 * Busca la sugerncia echa a la apelacion de un estudiante
	 * @param cedula
	 * @param codigoLapso
	 * @param instancia
	 * @param estado
	 * @return List<ApelacionEstadoApelacion> buscarSugerencia lista con las sugerencias
	 */
	@Query("select a from ApelacionEstadoApelacion a where a.id.cedulaEstudiante = :cedula "
			+ " and a.id.codigoLapso = :codigoLapso and a.id.idInstanciaApelada = :instancia and " +
			" a.id.idEstadoApelacion = :estado")	
	public List<ApelacionEstadoApelacion> buscarSugerencia(
			@Param("cedula") String cedula,
			@Param("codigoLapso") String codigoLapso,
			@Param("instancia") Integer instancia,
			@Param("estado") Integer estado);
}