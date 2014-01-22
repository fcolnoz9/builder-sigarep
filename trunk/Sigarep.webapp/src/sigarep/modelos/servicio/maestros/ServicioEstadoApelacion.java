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
import sigarep.modelos.data.maestros.Recaudo;
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
	public void guardarEstadoApelacion(EstadoApelacion estadoApelacion) {
		ea.save(estadoApelacion);
	}
//	public List<EstadoApelacion> listadoEstadoApelacionporInstancia(Integer idInstancia) {
//		List<EstadoApelacion> listaEstadoApelacionporInstancia=ea.buscarEstadoPorInstancia(idInstancia);
//	    return listaEstadoApelacionporInstancia;
//	}
	public List<EstadoApelacion> listadoEstadoApelacionActivas() {
		List<EstadoApelacion> ListaEstadoApelacion = ea.buscarEstadoApelacionActivas();
		return ListaEstadoApelacion;
	}
	public EstadoApelacion buscarEstadoNombre(String nombreEstado) {
		EstadoApelacion estadoapelacion=ea.buscarEstadoPorNombre(nombreEstado);
      return estadoapelacion;
	}

	// Busca en la lista de EstadoApelacion
//	public List<EstadoApelacion> buscarEstadoApelacion(EstadoApelacionFiltros filtros) {
//		List<EstadoApelacion> result = new LinkedList<EstadoApelacion>();
//		String nombreEstado = filtros.getNombreEstado().toLowerCase();
//	    String descripcion = filtros.getDescripcion().toLowerCase();
//
//		if (nombreEstado == null || descripcion == null) {
//			result = listadoEstadoApelacionActivas();
//		} else {
//			for (EstadoApelacion esta : listadoEstadoApelacionActivas()) {
//				if (esta.getNombreEstado().toLowerCase().contains(nombreEstado)
//						&& esta.getDescripcion().toLowerCase().contains(descripcion)) {
//					result.add(esta);
//				}
//			}
//		}
//		return result;
//	}
//
}

