package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.ApelacionMomentoPK;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.repositorio.transacciones.IApelacionMomentoDAO;

@Service("servicioapelacionmomento")
public class ServicioApelacionMomento {

	private @Autowired IApelacionMomentoDAO iApelacionMomentoDAO;
	
	public ApelacionMomento guardar(ApelacionMomento apelacionmomento) {
		return iApelacionMomentoDAO.save(apelacionmomento);
	}
	
	public void eliminar(ApelacionMomento apelacionmomento){
		iApelacionMomentoDAO.delete(apelacionmomento);
	}
	
	public List<ApelacionMomento> buscarTodos() {
		return iApelacionMomentoDAO.findAll();
	}

	public int contarTodos() {
		return iApelacionMomentoDAO.findAll().size();
	}

	public ApelacionMomento crear() {
		return new ApelacionMomento();
	}
}
