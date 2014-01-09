package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.repositorio.transacciones.IApelacionEstadoApelacionDAO;

@Service("servicioapelacionestadoapelacionrecurso")
public class ServicioApelacionEstadoApelacionRecurso {
private @Autowired IApelacionEstadoApelacionDAO iApelacionEstadoApelacionDAO;
	
	public ApelacionEstadoApelacion guardar(ApelacionEstadoApelacion apelacionmomento) {
		return iApelacionEstadoApelacionDAO.save(apelacionmomento);
	}
	
	public void eliminar(ApelacionEstadoApelacion apelacionmomento){
		iApelacionEstadoApelacionDAO.delete(apelacionmomento);
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
