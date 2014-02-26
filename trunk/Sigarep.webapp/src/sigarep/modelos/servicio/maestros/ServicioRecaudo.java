package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.repositorio.maestros.IRecaudoDAO;

/*
 * @ (#) ServicioRecaudo.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/**
 ** Servicio del registro del maestro "Recaudo"
 * @Author BUILDER
 * @Version 1.0, 16/12/13
 */

@Service("serviciorecaudo")
public class ServicioRecaudo {
	private @Autowired IRecaudoDAO iRecaudoDAO;
	
	//metodo que permite Guardar
	public void guardarRecaudo(Recaudo recaudo){
		if (recaudo.getIdRecaudo() != null)
			iRecaudoDAO.save(recaudo);
		else{
			recaudo.setIdRecaudo(iRecaudoDAO.buscarUltimoID()+1);
			iRecaudoDAO.save(recaudo);
		}
	}
	
	//metodo que permite eliminar
	public void eliminarRecaudo(Integer idRecaudo) {
		Recaudo rec = iRecaudoDAO.findOne(idRecaudo);
		rec.setEstatus(false);
		iRecaudoDAO.save(rec);
	}
	
	public List<Recaudo> listadoRecaudosPorMotivo(TipoMotivo motivo) {
	    return iRecaudoDAO.findByTipoMotivoAndEstatusTrue(motivo);
	}
	
	public Recaudo buscarRecaudoPorNombre(String nombreRecaudo) {
	    return iRecaudoDAO.findByNombreRecaudo(nombreRecaudo);
	}
	
	public List<Recaudo> listadoRecaudosActivos() {
	    return iRecaudoDAO.findByEstatusTrue();
	}
	
	//Busca en la lista de Recaudo
	public List<Recaudo> buscarRecaudo(String nombreRecaudo,String  nombreTipoMotivo) {
		List<Recaudo> resultado = new LinkedList<Recaudo>();	
		if (nombreRecaudo == null || nombreTipoMotivo==null ) {
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
	
	/** lista de recaudos faltantes por entregar de un estudiante sancionado
	 *  en la segunda apelacion
	 * @param cedula
	 * @return lista de recaudos faltantes por entregar
	 */
	public List<Recaudo> buscarRecaudosVerificarRecaudosII(String cedula) {
		return iRecaudoDAO.buscarRecaudosVerificarRecaudosII(cedula);
	}
	
	/** lista de recaudos faltantes por entregar de un estudiante sancionado
	 * en la tercera apelacion
	 * @param cedula
	 * @return lista de recaudos faltantes por entregar
	 */
	public List<Recaudo> buscarRecaudosVerificarRecaudosIII(String cedula) {
		return iRecaudoDAO.buscarRecaudosVerificarRecaudosIII(cedula);
	}
	public List<Recaudo> buscarRecaudosPorApelacion(String cedula, String codigoLapso, Integer idInstancia) {	
		return iRecaudoDAO.listadoRecaudosPorApelacion(cedula, codigoLapso, idInstancia);
	}
}
