package sigarep.modelos.servicio.maestros;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.repositorio.maestros.IEstadoApelacionDAO;

/**
 * Clase  ServicioEstadoApelacion (Servicio para la Clase
 * EstadoApelacion)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("servicioestadoapelacion")
public class ServicioEstadoApelacion {
	private @Autowired
	IEstadoApelacionDAO ea;
	
	/** Guardar Estado de Apelación 
	 * @return guarda el objeto
	 * @param el objeto EstadoApelacion
	 * @throws No dispara ninguna excepción.
	 */
	public void guardarEstadoApelacion(EstadoApelacion estadoApelacion) {
		if (estadoApelacion.getIdEstadoApelacion() != null)
			ea.save(estadoApelacion);
		else{
			estadoApelacion.setIdEstadoApelacion(ea.buscarUltimoID()+1);
			ea.save(estadoApelacion);
		}
	}
	
	/** Lista de Estados de Apelación 
	 * @return Lista de los Estados de Apelacion registrados y activos
	 * @param vacio
	 * @throws No dispara ninguna excepción.
	   */
	public List<EstadoApelacion> listadoEstadoApelacionActivas() {
		List<EstadoApelacion> ListaEstadoApelacion = ea.findByEstatusTrue();
		return ListaEstadoApelacion;
	}
	
	/** Buscar Estados de Apelación por nombre
	 * @return el estado de apelacion buscado si existe
	 * @param nombre del estado de apelación
	 * @throws No dispara ninguna excepción.
	   */
	public EstadoApelacion buscarEstadoNombre(String nombreEstado) {
		EstadoApelacion estadoapelacion=ea.findByNombreEstado(nombreEstado);
      return estadoapelacion;
	}

	/**
	 * Busca los estados de apelación de una instancia
	 * @param el id de la instancia
	 * @return lista de estados de una instancia
	 */
	public List<EstadoApelacion> buscarEstados(int instancia) {
		List<EstadoApelacion> listaEstados = ea.buscarEstados(instancia);
		return listaEstados;
	}

	public boolean existePrioridad(Integer prioridadEjecucion) {
		EstadoApelacion estadoApelacion;
		estadoApelacion = ea.findByPrioridadEjecucion(prioridadEjecucion);
		if (estadoApelacion != null)
			return true;
		else
			return false;
	}
}