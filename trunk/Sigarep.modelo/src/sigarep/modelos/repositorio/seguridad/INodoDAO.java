package sigarep.modelos.repositorio.seguridad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sigarep.modelos.data.seguridad.Nodo;

/**
 * Clase INodoDAO
 * @author BUILDER
 * @version 1.0
 * @since 15/12/2013
 */
public interface INodoDAO extends JpaRepository<Nodo,Integer> {

	/**
	 * Busca las todos los nodos que poseen padre con id == i
	 * @return List<Nodo> Lista de nodos con idPadre == i
	 */
	
	public List<Nodo> findByPadre(int i);

}//Fin Clase INodoDAO
