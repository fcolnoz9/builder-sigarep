package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ListaApelacionEstadoApelacion;


public interface IServicioApelacion {
	
public List<ListaApelacionEstadoApelacion> buscarApelaciones(Estudiante estudiante, EstudianteSancionado estudiantesancionado,
		SolicitudApelacion solicitudapelacion,
		ApelacionEstadoApelacion apelacionEstadoApelacion, EstadoApelacion estadoApelacion);

}
