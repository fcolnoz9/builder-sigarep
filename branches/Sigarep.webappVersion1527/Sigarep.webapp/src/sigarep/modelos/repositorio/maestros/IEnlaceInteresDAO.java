package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.EnlaceInteres;

/** IEnlaceInteresDAO
 *  Permite el uso de query para consultas. se utiliza en ServicioEnlaceInteres.
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.0
 * @since 22/01/14
 */
public interface IEnlaceInteresDAO extends JpaRepository<EnlaceInteres, Integer> {

	/**
	 * Busca las todas los Enlaces de Interes que poseen estatus == true
	 * @return List<EnlacesInteres> Lista de Enlaces con estatus == true
	 */
	public List<EnlaceInteres> findByEstatusTrue();
		
	/**
	 * Busca el ultimo id insertado en la tabla Banner
	 * @return Ultimo id insertado en la tabla Banner
	 */
	@Query("SELECT COALESCE(MAX(ei.idEnlace),0) FROM EnlaceInteres AS ei")
	public int buscarUltimoID();
}
