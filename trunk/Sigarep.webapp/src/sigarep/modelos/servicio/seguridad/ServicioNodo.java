package sigarep.modelos.servicio.seguridad;


import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.repositorio.seguridad.INodoDAO;
// El servicio interactua con la base de datos

@Service("servicionodo") //Definiendo la variable servicio
public class ServicioNodo{
	private @Autowired INodoDAO iNodoDAO;

	public List<Nodo> listadoArbol() {
		List<Nodo> children=iNodoDAO.findAll();
	    return children ;
	}
	
	public List<Nodo> buscarPadre(Integer idPadre) {
		List<Nodo> children=iNodoDAO.findByPadre(idPadre);
	    return children ;
	}
	public Nodo buscarNodo(Integer idNodo) {
		Nodo nodo=iNodoDAO.findOne(idNodo);
	    return nodo ;
	}
	
	
}

	
