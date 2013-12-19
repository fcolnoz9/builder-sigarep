package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.repositorio.maestros.IEnlaceInteresDAO;

@Service("servicioenlacesinteres")
public class ServicioEnlaceInteres {
	private @Autowired IEnlaceInteresDAO enlaceinteres;

	public void guardarEnlace(EnlaceInteres enlace) {
		enlaceinteres.save(enlace);
	}	

	public void eliminar(Integer idEnlace) {
		enlaceinteres.delete(idEnlace);
	}


	public List<EnlaceInteres> listadoEnlaceInteres() {
		List<EnlaceInteres> listaEnlace = enlaceinteres.findAll();
		return listaEnlace;
	}

	public List<EnlaceInteres> buscarEnlacesNombre(String nombreEnlace){
		List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
		if (nombreEnlace==null || "".equals(nombreEnlace)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoEnlaceInteres();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (EnlaceInteres e: listadoEnlaceInteres()){
				if (e.getNombreEnlace().toLowerCase().contains(nombreEnlace.toLowerCase())||
					e.getDescripcion().toLowerCase().contains(nombreEnlace.toLowerCase())||
					e.getDireccionEnlace().toLowerCase().contains(nombreEnlace.toLowerCase())){
					result.add(e);
				}
			}
		}
		return result;
	}	
		
		public List<EnlaceInteres> buscarEnlacesCodigo(Integer id_enlace){
			List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
			if (id_enlace==null || "".equals(id_enlace)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
				result = listadoEnlaceInteres();
			}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
				for (EnlaceInteres ei: listadoEnlaceInteres()){
					if (ei.getIdEnlace().toString().contains(id_enlace.toString())||
						ei.getNombreEnlace().toLowerCase().contains(id_enlace.toString())||
						ei.getDescripcion().toLowerCase().contains(id_enlace.toString())||
						ei.getDireccionEnlace().toLowerCase().contains(id_enlace.toString())){
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
