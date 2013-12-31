package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;

@Service("serviciosolicitudapelacion")
public class ServicioSolicitudApelacion {

	private @Autowired ISolicitudApelacionDAO iSolicitudApelacionDAO;
	
	public SolicitudApelacion guardar(SolicitudApelacion solicitudapelacion) {
		return iSolicitudApelacionDAO.save(solicitudapelacion);
	}
	
	public void eliminar(SolicitudApelacion solicitudapelacion){
		iSolicitudApelacionDAO.delete(solicitudapelacion);
	}
	
	public List<SolicitudApelacion> buscarTodos() {
		return iSolicitudApelacionDAO.findAll();
	}

	public int contarTodos() {
		return iSolicitudApelacionDAO.findAll().size();
	}

	public SolicitudApelacion crear() {
		return new SolicitudApelacion();
	}
}
