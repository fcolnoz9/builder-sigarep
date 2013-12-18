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
import sigarep.modelos.repositorio.maestros.ITipoMotivoDAO;

@Service("serviciotipomotivo")
public class ServicioTipoMotivo {
	private @Autowired ITipoMotivoDAO tipomotivo;
	
	//metodo que permite Guardar
	public void guardar(TipoMotivo tipo){
		tipomotivo.save(tipo);
	}
	
	//metodo que permite eliminar
	public void eliminar(Integer idTipoMotivo) {
		tipomotivo.delete(idTipoMotivo);
	}
	public List<TipoMotivo> listadoTipoMotivo() {
		List<TipoMotivo> TipoMotivoLista=tipomotivo.findAll();
	    return TipoMotivoLista ;
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
