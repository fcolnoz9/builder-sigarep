package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.SancionMaestro;

/**
 * Repositorio ISancionMaestroDAO: Repositorio relacionado con el Maestro SancionMaestro.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface ISancionMaestroDAO extends JpaRepository<SancionMaestro, Integer> {

	/**
	 * Busca las Sanciones activas, es decir, que poseen estatus == true
	 * 
	 * @return List<SancionMaestro> Lista de Sanciones con estatus == true
	 */
	public List<SancionMaestro> findByEstatusTrue();
	
	/**
	 * Busca el último id insertado en la tabla SancionMaestro
	 * 
	 * @return Último id insertado en la tabla SancionMaestro
	 */
	@Query("SELECT COALESCE(MAX(sm.idSancion),0) FROM SancionMaestro AS sm")
	public int buscarUltimoID();
}
