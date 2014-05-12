package sigarep.modelos.repositorio.transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.Soporte;

/**
 * Repositorio ISoporteDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 20/12/2013
 * @last 12/05/2014
 */
public interface ISoporteDAO extends JpaRepository<Soporte, Integer> {
	
	/**
	 * Busca el soporte del recaudo entregado por un estudiante sancionado
	 * @param idRecaudo
	 * @param idTipoMotivo
	 * @param idInstanciaApelada
	 * @param cedula
	 * @param codigoLapso
	 * @return los datos del soporte
	 */
	@Query("SELECT s FROM Soporte AS s WHERE s.recaudoEntregado.id.idRecaudo = :idRecaudo " +
			"AND s.recaudoEntregado.id.idTipoMotivo = :idTipoMotivo " +
			"AND s.recaudoEntregado.id.idInstanciaApelada = :idInstanciaApelada " +
			"AND s.recaudoEntregado.id.cedulaEstudiante = :cedula " +
			"AND s.recaudoEntregado.id.codigoLapso = :codigoLapso")
	public Soporte buscarSoportePorIdRecaudoEntregado(@Param("idRecaudo")Integer idRecaudo,
			@Param("idTipoMotivo")Integer idTipoMotivo, @Param("idInstanciaApelada")Integer idInstanciaApelada,
			@Param("cedula")String cedula, @Param("codigoLapso")String codigoLapso);
	
	/**
	 * Busca el ultimo id insertado en la tabla Soporte
	 * @return Ultimo id insertado en la tabla Soporte
	 */
	@Query("SELECT COALESCE(MAX(s.idSoporte),0) FROM Soporte AS s")
	public int buscarUltimoID();
}
