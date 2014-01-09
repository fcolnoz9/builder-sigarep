package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.repositorio.transacciones.IMotivoDAO;

@Service("serviciomotivos")
public class ServicioMotivos {

	private @Autowired IMotivoDAO iMotivoDAO;
	
	
	public Motivo guardar(Motivo motivo) {
		return iMotivoDAO.save(motivo);
	}
}
