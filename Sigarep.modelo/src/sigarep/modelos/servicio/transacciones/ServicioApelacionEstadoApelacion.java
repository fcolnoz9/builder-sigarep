package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.repositorio.transacciones.IApelacionEstadoApelacionDAO;

/**
 * Clase ServicioApelacionEstadoApelacion : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla ApelacionEstadoApelacion 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("servicioapelacionestadoapelacion")
public class ServicioApelacionEstadoApelacion {

	// Atributos de la clase
	private @Autowired IApelacionEstadoApelacionDAO iApelacionEstadoApelacionDAO;
	
	/**
	 * Guarda una apelación con un estado de apelación 
	 * @param apelacionestadoapelacion
	 * @throws No dispara ninguna excepción.
	 */
	public ApelacionEstadoApelacion guardar(ApelacionEstadoApelacion apelacionestadoapelacion) {
		return iApelacionEstadoApelacionDAO.save(apelacionestadoapelacion);
	}
	
	/**
	 * Elimina una apelación en un estado de apelación
	 * @param apelacionestadoapelacion
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(ApelacionEstadoApelacion apelacionestadoapelacion){
		iApelacionEstadoApelacionDAO.delete(apelacionestadoapelacion);
	}
	
	/**
	 * Busca la lista de todas las apelaciones por estado de apelación 
	 * @return List<ApelacionEstadoApelacion> Lista de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<ApelacionEstadoApelacion> buscarTodos() {
		return iApelacionEstadoApelacionDAO.findAll();
	}
	
	/**
	 * Cuenta todas las apelaciones 
	 * @return Número de  ApelacionEstadoApelacion contadas
	 * @throws No dispara ninguna excepción.
	 */
	public int contarTodos() {
		return iApelacionEstadoApelacionDAO.findAll().size();
	}

	/**
	 * Nueva apelación con un estado de apelación 
	 * @return Objeto ApelacionEstadoApelacion
	 * @throws No dispara ninguna excepción.
	 */
	public ApelacionEstadoApelacion crear() {
		return new ApelacionEstadoApelacion();
	}

	/**
	 * Busca un historial de una apelación en un estado de apelación
	 * @param cedula, codigoLaso, idInstancia
	 * @return List<ApelacionEstadoApelacion> Lista de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<ApelacionEstadoApelacion> buscarApelacionHistorial(String cedula, String codigoLaso, Integer idInstancia) {
		return iApelacionEstadoApelacionDAO.buscarApelacionHistorial(cedula, codigoLaso, idInstancia);
	}
	
	/**
	 * Busca una sugerencia para una apelación en un estado de apelación
	 * @param cedula, codigoLapso, instancia, estado
	 * @return List<ApelacionEstadoApelacion> Lista de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<ApelacionEstadoApelacion> buscarSugerencia(String cedula, String codigoLapso, Integer instancia, Integer estado) {
		return iApelacionEstadoApelacionDAO.buscarSugerencia(cedula, codigoLapso, instancia, estado);
}
}