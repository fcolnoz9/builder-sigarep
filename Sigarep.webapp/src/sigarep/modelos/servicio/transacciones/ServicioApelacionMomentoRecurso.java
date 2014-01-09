package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.repositorio.transacciones.IApelacionEstadoApelacionDAO;

@Service("servicioapelacionmomentorecurso")
public class ServicioApelacionMomentoRecurso {
private @Autowired IApelacionEstadoApelacionDAO iApelacionMomentoDAO;
	
	public ApelacionEstadoApelacion guardar(ApelacionEstadoApelacion apelacionmomento) {
		return iApelacionMomentoDAO.save(apelacionmomento);
	}
	
	public void eliminar(ApelacionEstadoApelacion apelacionmomento){
		iApelacionMomentoDAO.delete(apelacionmomento);
	}
	
	public List<ApelacionEstadoApelacion> buscarTodos() {
		return iApelacionMomentoDAO.findAll();
	}

	public int contarTodos() {
		return iApelacionMomentoDAO.findAll().size();
	}

	public ApelacionEstadoApelacion crear() {
		return new ApelacionEstadoApelacion();
	}

}
