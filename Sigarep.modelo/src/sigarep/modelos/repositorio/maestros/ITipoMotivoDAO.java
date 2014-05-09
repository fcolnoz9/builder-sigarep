package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.TipoMotivo;

/**
 * Repositorio ITipoMotivoDAO: Repositorio relacionado con el Maestro TipoMotivo.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface ITipoMotivoDAO extends JpaRepository<TipoMotivo, Integer> {

	/**
	 * Busca los Tipos de Motivos activos, es decir, que poseen estatus == true 
	 * 
	 * @return List<TipoMotivo> Lista de Tipos de Motivos con estatus == true
	 */
	public List<TipoMotivo> findByEstatusTrue();

	/**
	 * Busca los Tipos de Motivos activos que no son protegidos por el sistema
	 * 
	 * @return List<TipoMotivo> Lista de Tipos de Motivos activos no protegidos por el sistema
	 */
	public List<TipoMotivo> findByProtegidoFalseAndEstatusTrue();

	/**
	 * Busca el último id insertado en la tabla TipoMotivo
	 * 
	 * @return Último id insertado en la tabla TipoMotivo
	 */
	@Query("SELECT COALESCE(MAX(tp.idTipoMotivo),0) FROM TipoMotivo AS tp")
	public int buscarUltimoID();
}
