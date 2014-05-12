package sigarep.modelos.servicio.seguridad;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.repositorio.seguridad.INodoDAO;

/**
* Clase ServicioNodo Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla Nodo 
* @author Equipo Builder
* @version 1.0
* @since 15/12/2013
* @last 10/05/2014
*/

@Service("servicionodo")
public class ServicioNodo{
	// Atributos de la clase
	private @Autowired INodoDAO iNodoDAO;
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Busca una lista de todos los nodos del menú árbol
	 * @return List<Nodo> nodos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Nodo> listadoArbol() {
		List<Nodo> children=iNodoDAO.findAll();
	    return children ;
	}
	
	/**
	 *  Busca una lista de nodos dado el id del nodo padre
	 * @param idPadre
	 * @return List<Nodo> nodos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Nodo> buscarNodosPorPadre(Integer idPadre) {
		List<Nodo> children=iNodoDAO.findByPadre(idPadre);
	    return children ;
	}
	
	/**
	 * Busca un nodo por id de nodo
	 * @param idNodo
	 * @return Objeto Nodo
	 * @throws Dispara una excepción si el nodo no existe.
	 */
	
	public Nodo buscarNodo(Integer idNodo) {
		Nodo nodo=iNodoDAO.findOne(idNodo);
	    return nodo ;
	}
	
	/**
	 * Busca una lista de los nodos (funciones) dado el id del grupo al que NO pertenecen.
	 * @param idGrupo
	 * @return List<Nodo> nodos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Nodo> funcionesGrupoNoPerteneceGrupo(Integer idGrupo) 
	{
		List<Nodo> results = new ArrayList<Nodo>();
		String queryStatement = "SELECT DISTINCT nodo.id, nodo.tipo, nodo.nombre_funcion, nodo.vinculo, nodo.estatus, nodo.padre, nodo.ruta_modal FROM sigarep.menu_arbol nodo WHERE nodo.tipo = 'F' AND nodo.id NOT IN (SELECT nodo.id FROM sigarep.menu_arbol nodo, sigarep.grupo g, sigarep.funcion_grupo fg WHERE nodo.id = fg.id_nodo AND fg.id_grupo = g.id_grupo AND g.id_grupo = "+ idGrupo +") ORDER BY nodo.id";
		Query query = em.createNativeQuery(queryStatement);
			@SuppressWarnings("unchecked")
			List<Object[]> resultSet = query.getResultList();
			for (Object[] resultRow : resultSet) {
				results.add(new Nodo((Integer) resultRow[0], (String) resultRow[1], (String) resultRow[2],
						(String) resultRow[3], (boolean) resultRow[4], (Integer) resultRow[5], (String) resultRow[6]));
			}
			return results;
	}
}