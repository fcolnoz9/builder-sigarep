package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.repositorio.maestros.IInstanciaApeladaDAO;
/**Clase ServicioInstanciaApelada
* Suministra los servicios al VMInstanciaApelada
* @author Builder
* @version 1.0
* @since 20/12/13
*/
@Service("servicioInstanciaApelada") //Definiendo la variable servicio
public class ServicioInstanciaApelada{
	private @Autowired IInstanciaApeladaDAO iInstancia ;

	/** Guardar Instancia apelada 
	 * @return nada
	 * @parameters el objeto InstanciaApelada
	 * @throws No dispara ninguna excepcion.
	   */
	public void guardar(InstanciaApelada pro) {
		iInstancia.save(pro);
	}
	
	/** Eliminar Instancia apelada 
	 * @return nada
	 * @parameters Entero codigoInstancia
	 * @throws No dispara ninguna excepcion.
	   */
	public void eliminar(Integer codigoInstancia){
		InstanciaApelada instanciaapelada = iInstancia.findOne(codigoInstancia);
		instanciaapelada.setEstatus(false);
		iInstancia.save(instanciaapelada);
	}
	
	/** Lista de Instancia Apelada 
	 * @return Lista de las InstanciasApeladas registradas y activas
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<InstanciaApelada> listadoInstanciaApelada() {
		List<InstanciaApelada> instanciaApeladaLista = iInstancia.buscarInstanciaActivo();
	    return instanciaApeladaLista ;
	}
	
	/** Buscar Instancia Apelada 
	 * @return Lista de InstanciasApeladas buscadas
	 * @parameters String Instancia, String Recurso
	 * @throws No dispara ninguna excepcion.
	   */
	public List<InstanciaApelada> buscarInstancia(String instancia, String recurso) {
		List<InstanciaApelada> resultado = new LinkedList<InstanciaApelada>();
		if (instancia == null || recurso == null) {
			resultado = listadoInstanciaApelada();
		} else {
			for (InstanciaApelada inst : listadoInstanciaApelada()) {
				if (inst.getInstanciaApelada().toLowerCase().contains(instancia)
						&& inst.getNombreRecursoApelacion().toLowerCase().contains(recurso)) {
					resultado.add(inst);
				}
			}
		}
		return resultado;
	}
	
	/** Buscar Instancia Apelada 
	 * @return InstanciaApelada buscada
	 * @parameters Integer codigoInstancia
	 * @throws No dispara ninguna excepcion.
	   */
	public InstanciaApelada buscar(Integer codigoInstancia){
		return iInstancia.findOne(codigoInstancia);
	}
	
	/** Lista de Instancia Apelada 
	 * @return Lista de todas las InstanciasApeladas activas e inactivas
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<InstanciaApelada> buscarTodas(){
		return iInstancia.buscarTodas();
	}
	
	/** Lista de Instancia Apelada 
	 * @return Lista de las InstanciasApeladas registradas y activas
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<InstanciaApelada> buscarTodasLasInstancias(){
		return iInstancia.buscarInstanciaActivo();
	}
}