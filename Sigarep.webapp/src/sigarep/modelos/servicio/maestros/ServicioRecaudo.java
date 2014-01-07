package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.repositorio.maestros.IRecaudoDAO;

@Service("serviciorecaudo")
public class ServicioRecaudo {
	private @Autowired IRecaudoDAO iRecaudoDAO;
	
	//metodo que permite Guardar
	public void guardarRecaudo(Recaudo recaudo){
		iRecaudoDAO.save(recaudo);
	}
	
	//metodo que permite eliminar
	public void eliminarRecaudo(Integer idRecaudo) {
		Recaudo rec = iRecaudoDAO.findOne(idRecaudo);
		rec.setEstatus(false);
		iRecaudoDAO.save(rec);
	}
	
	public List<Recaudo> listadoRecaudosPorMotivo(Integer idMotivo) {
		List<Recaudo> listaRecaudosPorMotivo=iRecaudoDAO.buscarRecaudosPorMotivo(idMotivo);
	    return listaRecaudosPorMotivo;
	}
	
	public Recaudo buscarRecaudoNombre(String nombreRecaudo) {
		Recaudo recaudo=iRecaudoDAO.buscarRecaudoPorNombre(nombreRecaudo);
	    return recaudo;
	}
		
}
