package sigarep.modelos.servicio.seguridad;


import java.util.ArrayList;
import java.util.Date;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.data.transacciones.HistoricoEstudiante;
import sigarep.modelos.repositorio.seguridad.INodoDAO;
// El servicio interactua con la base de datos

@Service("servicionodo") //Definiendo la variable servicio
public class ServicioNodo{
	private @Autowired INodoDAO iNodoDAO;
	@PersistenceContext
	private EntityManager em;
	public List<Nodo> listadoArbol() {
		List<Nodo> children=iNodoDAO.findAll();
	    return children ;
	}
	
	public List<Nodo> buscarNodosPorPadre(Integer idPadre) {
		List<Nodo> children=iNodoDAO.findByPadre(idPadre);
	    return children ;
	}
	public Nodo buscarNodo(Integer idNodo) {
		Nodo nodo=iNodoDAO.findOne(idNodo);
	    return nodo ;
	}
	
	public List<Nodo> funcionesGrupoNoPerteneceGrupo(Integer idGrupo) 
	{
		List<Nodo> results = new ArrayList<Nodo>();
		String queryStatement = "SELECT DISTINCT nodo.id, nodo.tipo, nodo.nombre_funcion, nodo.vinculo, nodo.estatus, nodo.padre, nodo.ruta_modal FROM sigarep.menu_arbol nodo WHERE nodo.tipo = 'F' AND nodo.id NOT IN (SELECT nodo.id FROM sigarep.menu_arbol nodo, sigarep.grupo g, sigarep.funcion_grupo fg WHERE nodo.id = fg.id_nodo AND fg.id_grupo = g.id_grupo AND g.id_grupo = "+ idGrupo +") ORDER BY nodo.id";
		Query query = em.createNativeQuery(queryStatement);
//		try {
			@SuppressWarnings("unchecked")
			List<Object[]> resultSet = query.getResultList();
			

			for (Object[] resultRow : resultSet) {
				results.add(new Nodo((Integer) resultRow[0], (String) resultRow[1], (String) resultRow[2],
						(String) resultRow[3], (boolean) resultRow[4], (Integer) resultRow[5], (String) resultRow[6]));
			}
			return results;
	}
}

	
