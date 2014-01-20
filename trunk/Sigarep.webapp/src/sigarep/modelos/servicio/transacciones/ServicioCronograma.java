package sigarep.modelos.servicio.transacciones;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

//import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;
import sigarep.modelos.repositorio.transacciones.ICronogramaDAO;

@Service("serviciocronograma")
public class ServicioCronograma {
	@PersistenceContext
	private EntityManager emcro;
	
	private @Autowired
	ICronogramaDAO iCronograma;
	
	public void eliminarCronograma(CronogramaPK id) {
		Cronograma c = iCronograma.findOne(id);
		c.setEstatus(false);
		iCronograma.save(c);
	}
	
	public List<Cronograma> listadoCronograma() {
		List<Cronograma> cronogramaLista = iCronograma.buscar();
		return cronogramaLista;
	}
	
	public Cronograma guardar(Cronograma cronograma) {
		return iCronograma.save(cronograma);
	}
	public  List<Cronograma> buscarTodos() {
		return iCronograma.findAll();
	}
	
    public List<Cronograma> buscarTodosCronogramas(String codigoLapso) {
		List<Cronograma> listaCronogramas = iCronograma.buscarCronogramas(codigoLapso);
		return listaCronogramas;
	}
    
    public Date buscarUltimaFechaDelCronogramaActual(){
    	return iCronograma.buscarUltimaFechaCronogramaActual();
    }
	
}