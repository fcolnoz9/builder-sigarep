package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.repositorio.maestros.IActividadDAO;

/**
 * Clase ServicioActividad (Servicio relacionado con la 
 * Clase Actividad del cronograma)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("servicioactividad")
public class ServicioActividad {
	private @Autowired
	IActividadDAO iActividad;

	/**
	 * Guardar Actividad
	 * @param Actividad actividad
	 * @return Guarda el objeto
	 * @throws No dispara ninguna excepción.
	 */
	public void guardar(Actividad actividad) {
		if (actividad.getIdActividad() != null)
			iActividad.save(actividad);
		else {
			actividad.setIdActividad(iActividad.buscarUltimoID() + 1);
			iActividad.save(actividad);
		}
	}

	/**
	 * Eliminar Actividad
	 * @param Integer idActividad
	 * @return Elimina lógicamente el objeto
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(Integer id_actividad) {
		Actividad miActividad = iActividad.findOne(id_actividad);
		miActividad.setEstatus(false);
		iActividad.save(miActividad);
	}

	/**
	 * Listado de las Actividades
	 * @param
	 * @return Busca todas las actividades que estan en estatus TRUE
	 * @throws No dispara ninguna excepción.
	 */
	public List<Actividad> listadoActividad() {
		List<Actividad> actividadLista = iActividad.findByEstatusTrue();
		return actividadLista;
	}

	/**
	 * Buscar Actividad filtrando por nombre y responsable
	 * 
	 * @param String nombre, String responsable
	 * @return Busca una actividad por nombre y responsable
	 * @throws No dispara ninguna excepción.
	 */
	public List<Actividad> buscarActividad(String nombre, String responsable) {
		List<Actividad> resultado = new LinkedList<Actividad>();
		if (nombre == null || responsable == null) {
			resultado = listadoActividad();
		} else {
			for (Actividad act : listadoActividad()) {
				if (act.getNombre().toLowerCase().contains(nombre)
						&& act.getInstanciaApelada().getInstanciaApelada()
								.toLowerCase().contains(responsable)) {
					resultado.add(act);
				}
			}
		}
		return resultado;
	}
}