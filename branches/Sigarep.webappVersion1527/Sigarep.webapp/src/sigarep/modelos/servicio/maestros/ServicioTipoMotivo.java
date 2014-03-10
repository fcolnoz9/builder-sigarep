package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.repositorio.maestros.ITipoMotivoDAO;
//El servicio interactua con la base de datos

@Service("serviciotipomotivo")
//Definiendo la variable servicio
public class ServicioTipoMotivo {
	private @Autowired ITipoMotivoDAO tipomotivo;
	
	/**
	 *GuardarTipoMotivo
	 * 
	 * @param TipoMotivo tipo
	 * @return guarda el objeto 
	 * @throws No  dispara ninguna excepcion.
	 */

	public void guardarTipoMotivo(TipoMotivo tipo){
		if (tipo.getIdTipoMotivo() != null)
			tipomotivo.save(tipo);
		else{
			tipo.setIdTipoMotivo(tipomotivo.buscarUltimoID()+1);
			tipomotivo.save(tipo);
		}
	}
	
	public TipoMotivo buscarTipoMotivoPorId(Integer codigoTipoMotivo){
		return tipomotivo.findOne(codigoTipoMotivo);
	}
	
	/**
	 *Elimina logicamente un tipoMotivo
	 * @param idTipoMotivo
	 * @return objeto con status false
	 * @throws No  dispara ninguna excepcion.
	 */
	public void eliminarTipoMotivo(Integer idTipoMotivo) {
		TipoMotivo tip = tipomotivo.findOne(idTipoMotivo);
		tip.setEstatus(false);
		tipomotivo.save(tip);
	}
	
	public List<TipoMotivo> listadoTipoMotivo() {
	    return tipomotivo.findByEstatusTrue();
	}	
	
	/**
	 * Buscar tipo motivo no protegido
	 * @param 
	 * @return objeto tipo motivo
	 * @throws Tipo motivo no protegido
	 */
	public List<TipoMotivo> buscarTipoMotivoNoProtegido(){
		return tipomotivo.findByProtegidoFalseAndEstatusTrue();
	}
	
	/**
	 *fitrar TipoMotivo por nombre
	 * 
	 * @param String nombreTipoMotivo
	 * @return busca un tipoMotivo por nombre en el filtro
	 *         filtros() de VMTipoMotivo.
	 * @throws No  dispara ninguna excepcion.
	 */

	public List<TipoMotivo> filtrarTipoMotivo(String nombreTipoMotivo){
		List<TipoMotivo> result = new LinkedList<TipoMotivo>();
		if (nombreTipoMotivo==null || "".equals(nombreTipoMotivo)){
			result = listadoTipoMotivo();
		}else{
			for (TipoMotivo tip: listadoTipoMotivo()){
				if (tip.getNombreTipoMotivo().toLowerCase().contains(nombreTipoMotivo.toLowerCase())||
					tip.getDescripcion().toLowerCase().contains(nombreTipoMotivo.toLowerCase())){
					result.add(tip);
				}
			}
		}
		return result;
	}
	
}
