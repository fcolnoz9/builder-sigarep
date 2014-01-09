package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
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
}
