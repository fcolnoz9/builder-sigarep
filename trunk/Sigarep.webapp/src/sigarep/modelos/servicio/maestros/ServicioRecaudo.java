package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.RecaudoFiltro;
import sigarep.modelos.repositorio.maestros.IRecaudoDAO;

/*
 * @ (#) ServicioRecaudo.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
 ** Servicio del registro del maestro "Recaudo"
 * @ Author Beleanny Atacho
 * @ Version 1.0, 16/12/13
 */



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
	//Busca en la lista de Recaudo
	public List<Recaudo> buscarRecaudo(String nombreRecaudo,String  nombreTipoMotivo) {
		List<Recaudo> resultado = new LinkedList<Recaudo>();	
		if (nombreRecaudo == null ||nombreTipoMotivo==null ) {
			resultado = listadoRecaudosActivos();
		} else {
			for (Recaudo rec : listadoRecaudosActivos()) {
				if (rec.getNombreRecaudo().toLowerCase().contains(nombreRecaudo)
						&& rec.getTipoMotivo().getNombreTipoMotivo().toLowerCase()
						.contains(nombreTipoMotivo))
				{
					resultado.add(rec);
				}
			}
		}
		return resultado;
	}
	
	public List<Recaudo> filtrarRecaudos(String nombreRecaudo) {
		List<Recaudo> resultado = new LinkedList<Recaudo>();	
		if (nombreRecaudo == null) {
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
