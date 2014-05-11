package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.repositorio.maestros.IEnlaceInteresDAO;

/**
 * Clase  ServicioEnlaceInteres (Servicio para la
 * Clase EnlaceInteres)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("servicioenlacesinteres")
public class ServicioEnlaceInteres {
	private @Autowired
	IEnlaceInteresDAO enlaceinteres;

	/**
	 * Guardar Enlace
	 * 
	 * @param EnlaceInteres enlace
	 * @return Guarda el objeto
	 * @throws No dispara ninguna excepción.
	 */
	public void guardarEnlace(EnlaceInteres enlace) {
		if (enlace.getIdEnlace() != null)
			enlaceinteres.save(enlace);
		else{
			enlace.setIdEnlace(enlaceinteres.buscarUltimoID() + 1);
			enlaceinteres.save(enlace);
		}
	}

	/**
	 * Eliminar enlace
	 * 
	 * @param Integer idEnlace
	 * @return permite la eliminación física
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(Integer idEnlace) {
		EnlaceInteres enlaceBorrarFisico = enlaceinteres.findOne(idEnlace);
		enlaceinteres.delete(enlaceBorrarFisico);
	}

	/**
	 * Listado Enlace Interés
	 * 
	 * @param vacío
	 * @return listadoEnlaceInteres con estatus = true
	 * @throws No dispara ninguna excepción.
	 */
	public List<EnlaceInteres> listadoEnlaceInteres() {
		return enlaceinteres.findByEstatusTrue();
	}

	/**
	 *buscarEnlacesFiltro
	 * 
	 * @param String nombreEnlace, String direccionEnlace
	 * @return busca un enlace por nombre o dirección en el filtro
	 *         filtros() de VMenlaceInteres.
	 * @throws No  dispara ninguna excepción.
	 */
	public List<EnlaceInteres> buscarEnlacesFiltro(String nombreEnlace, String direccionEnlace) {
		List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
		if (nombreEnlace == null || direccionEnlace == null ) {// si el nombre es null o  vacio,el  resultado va  a ser la lista  completa de  todos los enlaces.
			result = listadoEnlaceInteres();
		} else {// caso contrario, se recorre toda la lista y busca los enlaces con el nombre indicado en la caja de texto y tambien busca todos los que tengan las letras iniciales de ese nombre. Realiza la busqueda por nombre y por inicial del nombre.
			for (EnlaceInteres e : listadoEnlaceInteres()) {
				if (e.getNombreEnlace().toLowerCase().contains(nombreEnlace) && 
					e.getDireccionEnlace().toLowerCase().contains(direccionEnlace)) {
					result.add(e);
				}
			}
		}
		return result;
	}	
}