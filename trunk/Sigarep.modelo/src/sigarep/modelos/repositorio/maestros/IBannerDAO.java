package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Banner;

/**
 * Repositorio Banner-IBannerDAO
 * 
 * @author BUILDER
 * @version 1.0
 * @since 12/12/2013
 */

public interface IBannerDAO extends JpaRepository<Banner, Integer> {

	/**
	 * Busca las todas los Banner que poseen estatus == true 
	 * @return List<Banner> Lista de Banner's con estatus == true
	 */
	public List<Banner> findByEstatusTrue();

	/**
	 * Busca el último id insertado en la tabla Banner
	 * @return Último id insertado en la tabla Banner
	 */
	@Query("SELECT COALESCE(MAX(b.idImagen),0) FROM Banner AS b")
	public int buscarUltimoID();
}
