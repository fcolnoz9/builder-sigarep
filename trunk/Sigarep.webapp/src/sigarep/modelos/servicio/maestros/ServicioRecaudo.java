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
	
	
	
	/**
	 * Guardar Recaudo
	 * @param Recaudod recaudo
	 * @return Guarda el objeto
	 * @throws No dispara ninguna excepcion.
	 */

	public void guardarRecaudo(Recaudo recaudo){
		if (recaudo.getIdRecaudo() != null)
			iRecaudoDAO.save(recaudo);
		else{
			recaudo.setIdRecaudo(iRecaudoDAO.buscarUltimoID()+1);
			iRecaudoDAO.save(recaudo);
		}
	}
	/**
	 * Eliminar Recaudo
	 * @param Integer idRecaudo
	 * @return Elimina lógicamente el objeto
	 * @throws No dispara ninguna excepcion.
	 */
	public void eliminarRecaudo(Integer idRecaudo) {
		Recaudo rec = iRecaudoDAO.findOne(idRecaudo);
		rec.setEstatus(false);
		iRecaudoDAO.save(rec);
	}
	/**
	 * Listado de 
	 * @param
	 * @return Busca todas las actividades que estan en estatus TRUE
	 * @throws No dispara ninguna excepcion.
	 */
	public List<Recaudo> listadoRecaudosPorMotivo(TipoMotivo motivo) {
	    return iRecaudoDAO.findByTipoMotivoAndEstatusTrue(motivo);
	}
	
	public Recaudo buscarRecaudoPorNombre(String nombreRecaudo) {
	    return iRecaudoDAO.findByNombreRecaudo(nombreRecaudo);
	}
	/**
	 * Listado de Recaudos
	 * @param
	 * @return Busca todas los recaudos que estan en estatus TRUE
	 * @throws No dispara ninguna excepcion.
	 */
	public List<Recaudo> listadoRecaudosActivos() {
	    return iRecaudoDAO.findByEstatusTrue();
	}
	/**
	 * Buscar en la lista de recaudos 
	 * 
	 * @param String nombreRecaudo, String  nombreTipoMotivo
	 * @return Busca un  recaudo por nombre y un nombre de motivo
	 * @throws No dispara ninguna excepcion.
	 */
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
	/**
	 * Buscar Recaudos filtrando por nombre 
	 * 
	 * @param String nombreRecaudo
	 * @return Busca un  recaudo por nombre 
	 * @throws No dispara ninguna excepcion.
	 */
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
