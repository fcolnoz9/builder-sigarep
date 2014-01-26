package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.repositorio.maestros.IEnlaceInteresDAO;

@Service("servicioenlacesinteres")
public class ServicioEnlaceInteres {
	private @Autowired
	IEnlaceInteresDAO enlaceinteres;

	/**
	 * Guardar Enlace
	 * 
	 * @param EnlaceInteres
	 *            enlace
	 * @return Guarda el objeto
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public void guardarEnlace(EnlaceInteres enlace) {
		enlaceinteres.save(enlace);
	}

	/**
	 * Eliminar enlace
	 * 
	 * @param Integer
	 *            idEnlace
	 * @return permite la eliminación física
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public void eliminar(Integer idEnlace) {
		EnlaceInteres enlaceBorrarFisico = enlaceinteres.findOne(idEnlace);
		enlaceinteres.delete(enlaceBorrarFisico);
	}

	/**
	 * Listado Enlace Interes
	 * 
	 * @param listadoEnlaceInteres
	 * @return buscar todos los enlaces
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<EnlaceInteres> listadoEnlaceInteres() {
		return enlaceinteres.findAll();
	}

	/**
	 * Buscar Enlaces Codigo
	 * 
	 * @param Integer
	 *            idEnlace
	 * @return busca un enlace por id.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<EnlaceInteres> buscarEnlacesCodigo(Integer idEnlace) {
		List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
		if (idEnlace == null) {// si el id es null,el resultado va a ser la
								// lista completa de todos los enlaces
			result = listadoEnlaceInteres();
		} else {// caso contrario, se recorre toda la lista y busca los enlaces
				// con el id indicado en el intbox. Realiza la busqueda por el
				// primer número y número completo.
			for (EnlaceInteres ei : listadoEnlaceInteres()) {
				if (ei.getIdEnlace().toString().contains(idEnlace.toString())) {
					result.add(ei);
				}
			}
		}
		return result;
	}

	/**
	 * Buscar Enlaces Nombre
	 * 
	 * @param String
	 *            nombreEnlace
	 * @return busca un enlace por nombre en el filtro
	 *         buscarEnlaceFiltroNombreEnlace() de VMenlaceInteres.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */

	public List<EnlaceInteres> buscarEnlacesNombre(String nombreEnlace) {
		List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
		if (nombreEnlace == null || "".equals(nombreEnlace)) {// si el nombre es null o  vacio,el  resultado va  a ser la lista  completa de  todos los enlaces.
			result = listadoEnlaceInteres();
		} else {// caso contrario, se recorre toda la lista y busca los enlaces con el nombre indicado en la caja de texto y tambien busca todos los que tengan las letras iniciales de ese nombre. Realiza la busqueda por nombre y por inicial del nombre.
			for (EnlaceInteres e : listadoEnlaceInteres()) {
				if (e.getNombreEnlace().toLowerCase()
						.contains(nombreEnlace.toLowerCase())) {
					result.add(e);
				}
			}
		}
		return result;
	}

	/**
	 * Buscar Enlaces Direccion
	 * 
	 * @param String
	 *            nombreEnlace
	 * @return busca un enlace por direccion en el filtro
	 *         buscarEnlaceFiltroDireccionEnlace() de VMenlaceInteres.
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<EnlaceInteres> buscarEnlacesDireccion(String direccionEnlace) {
		List<EnlaceInteres> result = new LinkedList<EnlaceInteres>();
		if (direccionEnlace == null || "".equals(direccionEnlace)) {// si la  dirección  es null o  vacio,el  resultado va a ser la lista  completa  de todos los  enlaces
			result = listadoEnlaceInteres();
		} else {// caso contrario, se recorre toda la lista y busca los enlaces  con la dirección indicada en la caja de texto. Realiza la busqueda con la dirección y con la inicial de la dirección.
			for (EnlaceInteres e : listadoEnlaceInteres()) {
				if (e.getDireccionEnlace().toLowerCase()
						.contains(direccionEnlace.toLowerCase())) {
					result.add(e);
				}
			}
		}
		return result;
	}
}// fin ServicioEnlaceInteres

