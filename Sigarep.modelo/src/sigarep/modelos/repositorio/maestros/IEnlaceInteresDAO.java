package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.EnlaceInteres;

/**
 * Repositorio IEnlaceInteresDAO: Repositorio relacionado con el Maestro EnlaceInteres.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface IEnlaceInteresDAO extends JpaRepository<EnlaceInteres, Integer> {

	/**
	 * Busca todos los Enlaces de Interés que poseen estatus == true
	 * 
	 * @return List<EnlacesInteres> Lista de Enlaces con estatus == true
	 */
	public List<EnlaceInteres> findByEstatusTrue();
		
	/**
	 * Busca el último id insertado en la tabla EnlaceInteres
	 * 
	 * @return Último id insertado en la tabla EnlaceInteres
	 */
	@Query("SELECT COALESCE(MAX(ei.idEnlace),0) FROM EnlaceInteres AS ei")
	public int buscarUltimoID();
}
