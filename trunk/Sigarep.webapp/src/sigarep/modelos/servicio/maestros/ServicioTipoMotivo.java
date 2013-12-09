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
	private @Autowired ITipoMotivoDAO tm;
	
	public void guardar(TipoMotivo tipo){
		tm.save(tipo);
	}
	public List<TipoMotivo> listadoTipoMotivo() {
		List<TipoMotivo> TipoMotivoLista=tm.findAll();
	    return TipoMotivoLista ;
	}
	
	public List<TipoMotivo> buscarP(String nombreTipoMotivo){
		List<TipoMotivo> result = new LinkedList<TipoMotivo>();
		if (nombreTipoMotivo==null || "".equals(nombreTipoMotivo)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoTipoMotivo();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (TipoMotivo t: listadoTipoMotivo()){
				if (t.getNombreTipoMotivo().toLowerCase().contains(nombreTipoMotivo.toLowerCase())||
					t.getDescripcion().toLowerCase().contains(nombreTipoMotivo.toLowerCase())){
					result.add(t);
				}
			}
		}
		return result;

	}
	
}
