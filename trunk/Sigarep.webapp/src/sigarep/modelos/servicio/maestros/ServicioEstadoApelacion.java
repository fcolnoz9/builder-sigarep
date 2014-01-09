package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.maestros.EstadoApelacionFiltros;
import sigarep.modelos.repositorio.maestros.IEstadoApelacionDAO;

/*
 * @ (#) ServicioEstadoApelacion.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
 ** Servicio del registro del maestro "EstadoApelacion"
 * @ Author Lilibeth Achji 
 * @ Version 1.0, 16/12/13
 */

@Service("servicioestadoapelacion")
public class ServicioEstadoApelacion {
	private @Autowired
	IEstadoApelacionDAO ea;

	// metodo para guardar
	public void guardar(EstadoApelacion estado) {
		ea.save(estado);
	}

	// metodo que permite eliminar
	public void eliminarEstadoApelacion(Integer estado) {
		EstadoApelacion estadoApelacion = ea.findOne(estado);
		estadoApelacion.setEstatus(false);
		ea.save(estadoApelacion);
	}

	public List<EstadoApelacion> listadoEstadoApelacion() {
		List<EstadoApelacion> EstadoApelacionLista = ea.buscarEstadosApelacionActivas();
		return EstadoApelacionLista;
	}

	// Busca en la lista de EstadoApelacion
	public List<EstadoApelacion> buscarEstadoApelacion(EstadoApelacionFiltros filtros) {
		List<EstadoApelacion> result = new LinkedList<EstadoApelacion>();
		String nombreEstado = filtros.getNombreEstado().toLowerCase();
		String descripcion = filtros.getDescripcion().toLowerCase();

		if (nombreEstado == null || descripcion == null) {
			result = listadoEstadoApelacion();
		} else {
			for (EstadoApelacion mome : listadoEstadoApelacion()) {
				if (mome.getNombreEstado().toLowerCase()
						.contains(nombreEstado)
						&& mome.getDescripcion().toLowerCase()
								.contains(descripcion)) {
					result.add(mome);
				}
			}
		}
		return result;
	}

}
