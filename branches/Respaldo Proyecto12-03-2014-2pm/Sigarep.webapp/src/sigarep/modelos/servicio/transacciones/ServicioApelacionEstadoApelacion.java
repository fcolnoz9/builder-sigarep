package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.repositorio.transacciones.IApelacionEstadoApelacionDAO;

@Service("servicioapelacionestadoapelacion")
public class ServicioApelacionEstadoApelacion {

	private @Autowired IApelacionEstadoApelacionDAO iApelacionEstadoApelacionDAO;
	
	public ApelacionEstadoApelacion guardar(ApelacionEstadoApelacion apelacionestadoapelacion) {
		return iApelacionEstadoApelacionDAO.save(apelacionestadoapelacion);
	}
	
	public void eliminar(ApelacionEstadoApelacion apelacionestadoapelacion){
		iApelacionEstadoApelacionDAO.delete(apelacionestadoapelacion);
	}
	
	public List<ApelacionEstadoApelacion> buscarTodos() {
		return iApelacionEstadoApelacionDAO.findAll();
	}

	public int contarTodos() {
		return iApelacionEstadoApelacionDAO.findAll().size();
	}

	public ApelacionEstadoApelacion crear() {
		return new ApelacionEstadoApelacion();
	}

	public List<ApelacionEstadoApelacion> buscarApelacionHistorial(String cedula, String codigoLaso, Integer idInstancia) {
		return iApelacionEstadoApelacionDAO.buscarApelacionHistorial(cedula, codigoLaso, idInstancia);
	}
	public List<ApelacionEstadoApelacion> buscarSugerencia(String cedula, String codigoLapso, Integer instancia, Integer estado) {
		return iApelacionEstadoApelacionDAO.buscarSugerencia(cedula, codigoLapso, instancia, estado);
}
}