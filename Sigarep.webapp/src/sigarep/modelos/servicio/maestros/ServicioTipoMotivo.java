package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.TipoMotivo;
import sigarep.modelos.data.maestros.TipoMotivoFiltros;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.repositorio.maestros.ITipoMotivoDAO;

@Service("serviciotipomotivo")
public class ServicioTipoMotivo {
	private @Autowired ITipoMotivoDAO tipomotivo;
	
	//metodo que permite Guardar
	public void guardarTipoMotivo(TipoMotivo tipo){
		tipomotivo.save(tipo);
	}
	
	public TipoMotivo buscarTipoMotivoPorCodigo(Integer codigoTipoMotivo){
		TipoMotivo tipmotivo = tipomotivo.findOne(codigoTipoMotivo);
		return tipmotivo;
	}
	
	//metodo que permite eliminar
	public void eliminarTipoMotivo(Integer tipo) {
		TipoMotivo tip = tipomotivo.findOne(tipo);
		tip.setEstatus(false);
		tipomotivo.save(tip);
	}
	
	public List<TipoMotivo> listadoTipoMotivo() {
		List<TipoMotivo> TipoMotivoLista=tipomotivo.buscarTipoMotivoActivas();
	    return TipoMotivoLista ;
	}
	
	public List<TipoMotivo> buscarTipoMotivo(TipoMotivoFiltros filtros) {
		List<TipoMotivo> result = new LinkedList<TipoMotivo>();
		String nombre = filtros.getNombre().toLowerCase();
		String descripcion = filtros.getDescripcion().toLowerCase();

		if (nombre == null || descripcion == null) {
			result = listadoTipoMotivo();
		} else {
			for (TipoMotivo tipo : listadoTipoMotivo()) {
				if (tipo.getNombreTipoMotivo().toLowerCase().contains(nombre)
						&& tipo.getDescripcion().toLowerCase()
								.contains(descripcion)) {
					result.add(tipo);
				}
			}
		}
		return result;
	}
	
	//Metodo de Busqueda
	public List<TipoMotivo> buscarP(String nombreTipoMotivo){
		List<TipoMotivo> result = new LinkedList<TipoMotivo>();
		if (nombreTipoMotivo==null || "".equals(nombreTipoMotivo)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los tipos de motivo
			// si el codigo es null o vacio,el resultado va a ser la lista completa de
			//todas los motivos
			result = listadoTipoMotivo();
		}else{//caso contrario se recorre toda la lista y busca los tipos de motivos con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre.
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
