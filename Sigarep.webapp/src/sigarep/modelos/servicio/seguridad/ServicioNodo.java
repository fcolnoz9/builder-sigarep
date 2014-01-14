package sigarep.modelos.servicio.seguridad;


import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.seguridad.Nodo;
import sigarep.modelos.repositorio.seguridad.INodo;
// El servicio interactua con la base de datos

@Service("snodo") //Definiendo la variable servicio
public class ServicioNodo{
	private @Autowired INodo ia;

	public List<Nodo> listadoArbol() {
		List<Nodo> children=ia.findAll();
	    return children ;
	}
	
	public List<Nodo> buscarPadre(Integer idPadre) {
		List<Nodo> children=ia.findByPadre(idPadre);
	    return children ;
	}
	public Nodo buscarNodo(Integer idNodo) {
		Nodo nodo=ia.findOne(idNodo);
	    return nodo ;
	}
	
	
}

	
