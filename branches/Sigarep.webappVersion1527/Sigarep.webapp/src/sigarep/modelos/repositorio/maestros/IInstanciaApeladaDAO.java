package sigarep.modelos.repositorio.maestros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.InstanciaApelada;

/**Clase IInstanciaApeladaDAO
* JPARepository para la Tabla InstanciaApelada
* @author Builder
* @version 1.0
* @since 20/12/13
*/

public interface IInstanciaApeladaDAO extends JpaRepository<InstanciaApelada, Integer> {
	
	/**
	 * Busca las todas las instancias apeladas que poseen estatus true
	 * @return List<InstanciaApelada> Lista de InstanciaApelada con estatus true
	 */
	public List<InstanciaApelada> findByEstatusTrue();
	
	/**
	 * Busca el ultimo id insertado en la tabla InstanciaApelada
	 * @return Ultimo id insertado en la tabla InstanciaApelada
	 */
	@Query("SELECT COALESCE(MAX(ia.idInstanciaApelada),0) FROM InstanciaApelada AS ia")
	public int buscarUltimoID();
}
