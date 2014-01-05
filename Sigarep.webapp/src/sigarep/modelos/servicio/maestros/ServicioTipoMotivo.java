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
import sigarep.modelos.repositorio.maestros.ITipoMotivoDAO;

@Service("serviciotipomotivo")
public class ServicioTipoMotivo {
	private @Autowired ITipoMotivoDAO tipomotivo;
	
	//metodo que permite Guardar
	public void guardarTipoMotivo(TipoMotivo tipo){
		tipomotivo.save(tipo);
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
	
}
