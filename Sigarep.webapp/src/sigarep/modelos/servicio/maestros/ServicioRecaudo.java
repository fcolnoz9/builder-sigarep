package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.RecaudoFiltro;
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
	
	public List<Recaudo> listadoRecaudosActivos() {
		List<Recaudo> listaRecaudos=iRecaudoDAO.buscaRecaudosActivos();
	    return listaRecaudos;
	}
	
	public List<Recaudo> buscarRecaudo(RecaudoFiltro filtros) {
		List<Recaudo> resultado = new LinkedList<Recaudo>();
		String nombreRecaudo = filtros.getNombreRecaudo().toLowerCase();
		//String  Nombre_tipo_motivo= filtros.getNombre_tipo_motivo().toLowerCase();
		if (nombreRecaudo == null ) {
			resultado = listadoRecaudosActivos();
		} else {
			for (Recaudo rec : listadoRecaudosActivos()) {
				if (rec.getNombreRecaudo().toLowerCase().contains(nombreRecaudo))
				{
					resultado.add(rec);
				}
			}
		}
		return resultado;
	}
	
	
}
