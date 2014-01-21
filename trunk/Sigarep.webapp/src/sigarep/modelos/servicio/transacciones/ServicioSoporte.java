package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.repositorio.transacciones.ISoporteDAO;

@Service("serviciosoporte")
public class ServicioSoporte {

	private @Autowired ISoporteDAO iSoporteDAO;
	
	
	public Soporte guardar(Soporte soporte) {
		return iSoporteDAO.save(soporte);
	}
	
	public void eliminar(Integer idSoporte){
		iSoporteDAO.delete(idSoporte);
	}
	
	public Soporte buscarSoportePorID(Integer idSoporte){
		return iSoporteDAO.findOne(idSoporte);
	}
}
