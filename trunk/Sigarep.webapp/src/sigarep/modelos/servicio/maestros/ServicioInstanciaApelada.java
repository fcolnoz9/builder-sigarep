package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.InstanciaApeladaFiltros;
import sigarep.modelos.repositorio.maestros.IInstanciaApeladaDAO;

@Service("servicioInstanciaApelada") //Definiendo la variable servicio
public class ServicioInstanciaApelada{
	private @Autowired IInstanciaApeladaDAO iInstancia ;

	public void guardar(InstanciaApelada pro) {
		iInstancia.save(pro);
	}
	public void eliminar(Integer codigoInstancia){
		InstanciaApelada instanciaapelada = iInstancia.findOne(codigoInstancia);
		instanciaapelada.setEstatus(false);
		iInstancia.save(instanciaapelada);
	}
	public List<InstanciaApelada> listadoInstanciaApelada() {
		List<InstanciaApelada> instanciaApeladaLista = iInstancia.buscarInstanciaActivo();
	    return instanciaApeladaLista ;
	}
	public List<InstanciaApelada> buscarInstancia(InstanciaApeladaFiltros filtros) {
		List<InstanciaApelada> resultado = new LinkedList<InstanciaApelada>();
		String nombreInstancia = filtros.getNombreInstancia().toLowerCase();
		String nombreRecurso = filtros.getNombreRecurso().toLowerCase();
		String descripcion = filtros.getDescripcion().toLowerCase();
		if (nombreInstancia == null || descripcion == null || nombreRecurso == null) {
			resultado = listadoInstanciaApelada();
		} else {
			for (InstanciaApelada inst : listadoInstanciaApelada()) {
				if (inst.getInstanciaApelada().toLowerCase().contains(nombreInstancia)
						&& inst.getDescripcion().toLowerCase().contains(descripcion)
						&& inst.getNombreRecursoApelacion().toLowerCase().contains(nombreRecurso)) {
					resultado.add(inst);
				}
			}
		}
		return resultado;
	}
	public InstanciaApelada buscar(Integer codigoInstancia){
		return iInstancia.findOne(codigoInstancia);
	}
	public List<InstanciaApelada> buscarTodas(){
		return iInstancia.buscarTodas();
	}
	public List<InstanciaApelada> buscarTodasLasInstancias(){
		return iInstancia.buscarInstanciaActivo();
	}
}
