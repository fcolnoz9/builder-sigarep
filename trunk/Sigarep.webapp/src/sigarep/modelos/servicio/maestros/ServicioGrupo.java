package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.Grupo;
import sigarep.modelos.data.maestros.Grupo;
import sigarep.modelos.repositorio.maestros.IGrupoDAO;
import sigarep.modelos.repositorio.maestros.IGrupoDAO;

@Service("serviciogrupo")
public class ServicioGrupo {
	private @Autowired IGrupoDAO iGrupo;
	
	public void guardar(Grupo grupo){
		iGrupo.save(grupo);
	}
	public void eliminar(Integer idGrupo) {
		Grupo miGrupo = iGrupo.findOne(idGrupo);
		miGrupo.setEstatus(false);
		iGrupo.save(miGrupo);
	}
	
	public List<Grupo> listadoGrupo() {
		List<Grupo> GrupoLista = iGrupo.buscarGruposActivos();
	    return GrupoLista; 
	}
	
	public List<Grupo> buscarP(String nombre){
		List<Grupo> result = new LinkedList<Grupo>();
		if (nombre==null || "".equals(nombre)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los s de motivo
			// si el codigo es null o vacio,el resultado va a ser la lista completa de
			//todas los motivos
			result = listadoGrupo();
		}else{//caso contrario se recorre toda la lista y busca los s de motivos con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre.
			for (Grupo gru: listadoGrupo()){
				if (gru.getNombre().toLowerCase().contains(nombre.toLowerCase())||
						gru.getDescripcion().toLowerCase().contains(nombre.toLowerCase())){
					result.add(gru);
				}
			}
		}
		return result;

	}
	
}
