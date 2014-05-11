package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.repositorio.maestros.ISancionMaestroDAO;

/**
 * Clase  ServicioSancionMaestro (Servicio para
 * la Clase SancionMaestro)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("serviciosancionmaestro")
public class ServicioSancionMaestro {
	private @Autowired
	ISancionMaestroDAO san;

	/**
	 *guardar sancion
	 * @param SancionMaestro
	 * @return Objeto guardado
	 * @throws No  dispara ninguna excepción.
	 */
	public void guardarSancion(SancionMaestro sanm) {
		if (sanm.getIdSancion() != null)
			san.save(sanm);
		else{
			sanm.setIdSancion(san.buscarUltimoID()+1);
			san.save(sanm);
		}
	}

	/**
	 *eliminar sancion
	 * @param Integer sanm
	 * @return Objeto con estatus false, eliminado lógicamente
	 * @throws No  dispara ninguna excepción.
	 */
	public void eliminarSancion(Integer sanm) {
		SancionMaestro sancion = san.findOne(sanm);
		sancion.setEstatus(false);
		san.save(sancion);
	}


	/**
	 *buscar sancion por ID
	 * 
	 * @param Integer idSancionMaestro
	 * @return Sanción
	 * @throws No  dispara ninguna excepción.
	 */
	public SancionMaestro buscarUnaSancion(Integer idSancionMaestro) {
		return san.findOne(idSancionMaestro);
	}
	
	/**
	 * lista de tipo de sanciones
	 * 
	 * @param vacío
	 * @return listaTipoSanciones con estatus = true
	 * @throws No dispara ninguna excepción.
	 */
	public List<SancionMaestro> listaTipoSanciones() {
		return san.findByEstatusTrue();
	}
	
	/**
	 * filtrar Sancion por nombre
	 * 
	 * @param String nombre
	 * @return busca una sanción por nombre
	 *         filtros() de VMenlaceInteres.
	 * @throws No  dispara ninguna excepción.
	 */
	public List<SancionMaestro> filtrarSancion(String nombre) {
		List<SancionMaestro> result = new LinkedList<SancionMaestro>();
		if (nombre == null) {
			result = listaTipoSanciones();
		} else {
			for (SancionMaestro sm : listaTipoSanciones()) {
				if (sm.getNombreSancion().toLowerCase().contains(nombre)) {
					result.add(sm);
				}
			}
		}
		return result;
	}
}