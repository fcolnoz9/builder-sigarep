package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.Momento;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.servicio.transacciones.ListaApelacionMomento;


public interface IServicioApelacion {
	
public List<ListaApelacionMomento> buscarApelaciones(Estudiante estudiante, EstudianteSancionado estudiantesancionado,
		SolicitudApelacion solicitudapelacion,
		ApelacionMomento apelacionmomento, Momento momento);

}
