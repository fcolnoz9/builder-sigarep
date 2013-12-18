package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.EnlacesInteres;
import sigarep.modelos.repositorio.maestros.IEnlacesInteresDAO;

@Service("servicioenlacesinteres")
public class ServicioEnlacesInteres {
	private @Autowired IEnlacesInteresDAO enlaceinteres;

	public void guardarEnlace(EnlacesInteres enlace) {
		enlaceinteres.save(enlace);
	}	

	public void eliminar(Integer id_enlace) {
		enlaceinteres.delete(id_enlace);
	}


	public List<EnlacesInteres> listadoEnlacesInteres() {
		List<EnlacesInteres> listaEnlace = enlaceinteres.findAll();
		return listaEnlace;
	}

	public List<EnlacesInteres> buscarEnlacesNombre(String nombre_enlace){
		List<EnlacesInteres> result = new LinkedList<EnlacesInteres>();
		if (nombre_enlace==null || "".equals(nombre_enlace)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoEnlacesInteres();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (EnlacesInteres e: listadoEnlacesInteres()){
				if (e.getNombre_enlace().toLowerCase().contains(nombre_enlace.toLowerCase())||
					e.getDescripcion_enlace().toLowerCase().contains(nombre_enlace.toLowerCase())||
					e.getDireccion_enlace().toLowerCase().contains(nombre_enlace.toLowerCase())){
					result.add(e);
				}
			}
		}
		return result;
	}	
		
		public List<EnlacesInteres> buscarEnlacesCodigo(Integer id_enlace){
			List<EnlacesInteres> result = new LinkedList<EnlacesInteres>();
			if (id_enlace==null || "".equals(id_enlace)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
				result = listadoEnlacesInteres();
			}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
				for (EnlacesInteres ei: listadoEnlacesInteres()){
					if (ei.getId_enlace().toString().contains(id_enlace.toString())||
						ei.getNombre_enlace().toLowerCase().contains(id_enlace.toString())||
						ei.getDescripcion_enlace().toLowerCase().contains(id_enlace.toString())||
						ei.getDireccion_enlace().toLowerCase().contains(id_enlace.toString())){
						result.add(ei);
					}
				}
			}
			return result;

	}
	
	/*public List<EnlacesInteres> buscarEnlaces(Integer id_enlace){
		List<EnlacesInteres> result = new LinkedList<EnlacesInteres>();
		if (id_enlace==null || "".equals(id_enlace)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoEnlacesInteres();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (EnlacesInteres ei: listadoEnlacesInteres()){
				if (ei.getId_enlace().toString().contains(id_enlace.toString())||
					ei.getNombre_enlace().toLowerCase().contains(id_enlace.toString())||
					ei.getDescripcion_enlace().toLowerCase().contains(id_enlace.toString())||
					ei.getDireccion_enlace().toLowerCase().contains(id_enlace.toString())){
					result.add(ei);
				}
			}
		}
		return result;

	}*/

}
