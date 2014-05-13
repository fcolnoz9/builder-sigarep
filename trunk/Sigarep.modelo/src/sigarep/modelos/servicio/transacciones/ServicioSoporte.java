package sigarep.modelos.servicio.transacciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.repositorio.transacciones.ISoporteDAO;

/**
 * Clase ServicioSoporte : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla Soporte 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("serviciosoporte")
public class ServicioSoporte {

	// Atributos de la clase
	private @Autowired ISoporteDAO iSoporteDAO;
	
	/**
	 * Guarda un soporte de los recaudos entregados
	 * @param soporte
	 * @return Objeto Soporte
	 * @throws No dispara ninguna excepción.
	 */
	public void guardar(Soporte soporte) {
		if (soporte.getIdSoporte() != null)
			iSoporteDAO.save(soporte);
		else{
			soporte.setIdSoporte(iSoporteDAO.buscarUltimoID()+1);
			iSoporteDAO.save(soporte);
		}
		
	}
	
	/**
	 * Elimina un soporte registrado
	 * @param idSoporte
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(Integer idSoporte){
		iSoporteDAO.delete(idSoporte);
	}
	
	/**
	 * Busca un soporte registrado por medio del id
	 * @param idSoporte
	 * @return Objeto Soporte
	 * @throws No dispara ninguna excepción.
	 */
	public Soporte buscarSoportePorID(Integer idSoporte){
		return iSoporteDAO.findOne(idSoporte);
	}

	/**
	 * Busca un soporte de recaudos entregados por un estudiante en su apelación 
	 * @param recaudoEntregadoPK
	 * @return Objeto Soporte
	 * @throws No dispara ninguna excepción.
	 */
	public Soporte buscarPorIdDeRecaudoEntregado(RecaudoEntregadoPK recaudoEntregadoPK) {
		Integer idRecaudo = recaudoEntregadoPK.getIdRecaudo();
		Integer idTipoMotivo = recaudoEntregadoPK.getIdTipoMotivo();
		Integer idInstancia = recaudoEntregadoPK.getIdInstanciaApelada();
		String cedula = recaudoEntregadoPK.getCedulaEstudiante();
		String codigoLapso = recaudoEntregadoPK.getCodigoLapso();
		return iSoporteDAO.buscarSoportePorIdRecaudoEntregado(idRecaudo, idTipoMotivo, idInstancia, cedula, codigoLapso);
	} 
}
