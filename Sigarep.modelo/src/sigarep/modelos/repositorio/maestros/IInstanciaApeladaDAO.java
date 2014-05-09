package sigarep.modelos.repositorio.maestros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.InstanciaApelada;

/**
 * Repositorio IInstanciaApeladaDAO: Repositorio relacionado con el Maestro InstanciaApelada.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface IInstanciaApeladaDAO extends JpaRepository<InstanciaApelada, Integer> {
	
	/**
	 * Busca las todas las instancias apeladas que poseen estatus == true
	 * 
	 * @return List<InstanciaApelada> Lista de instancias apeladas con estatus == true
	 */
	public List<InstanciaApelada> findByEstatusTrue();
	
	/**
	 * Busca el último id insertado en la tabla InstanciaApelada
	 * 
	 * @return Último id insertado en la tabla InstanciaApelada
	 */
	@Query("SELECT COALESCE(MAX(ia.idInstanciaApelada),0) FROM InstanciaApelada AS ia")
	public int buscarUltimoID();
}
