package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.TipoMotivo;

public interface ITipoMotivoDAO extends JpaRepository<TipoMotivo, Integer> {

	/**
	 * Busca los Tipos de Motivos activos, es decir, que poseen estatus true
	 * @return List<TipoMotivo> Lista de Tipos de Motivos con estatus true
	 */
	public List<TipoMotivo> findByEstatusTrue();
	
	/**
	 * Busca los Tipos de Motivos activos que no son protegidos por el sistema
	 * @return List<TipoMotivo> Lista de Tipos de Motivos activos no protegidos
	 */
	public List<TipoMotivo> findByProtegidoFalseAndEstatusTrue();
	
	/**
	 * Busca el ultimo id insertado en la tabla TipoMotivo
	 * @return Ultimo id insertado en la tabla TipoMotivo
	 */
	@Query("SELECT COALESCE(MAX(tp.idTipoMotivo),0) FROM TipoMotivo AS tp")
	public int buscarUltimoID();
}
