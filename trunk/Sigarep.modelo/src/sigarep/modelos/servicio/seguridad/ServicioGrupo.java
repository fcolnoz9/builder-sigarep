package sigarep.modelos.servicio.seguridad;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.repositorio.seguridad.IGrupoDAO;
import sigarep.modelos.repositorio.transacciones.IUsuarioGrupoDAO;

/**
* Clase ServicioGrupo : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla Grupo 
* @author Equipo Builder
* @version 1.0
* @since 15/12/2013
* @last 10/05/2014
*/
@Service("serviciogrupo")
public class ServicioGrupo {

	// Atributos de la clase
	@Autowired
	private IGrupoDAO iGrupoDAO;
	@Autowired
	private IUsuarioGrupoDAO iUsuarioGrupoDAO;

	/**
	 * Guarda un Grupo de usuarios del sistema
	 * @param grupo
	 * @throws No dispara ninguna excepción.
	 */
	
	public void guardarGrupo(Grupo grupo) {
		if (grupo.getIdGrupo() != null)
			iGrupoDAO.save(grupo);
		else{
			grupo.setIdGrupo(iGrupoDAO.buscarUltimoID() + 1);
			iGrupoDAO.save(grupo);
		}
	}

	/**
	 * Buscar Grupo dado su id de grupo
	 * @param idgrupo
	 * @return Objeto Grupo
	 * @throws NullPointerException si no
	 * encuentra el Grupo.
	 */
	
	public Grupo buscarGrupo(Integer idgrupo) {
		return iGrupoDAO.findOne(idgrupo);
	}
	
	/**
	 * Busca la lista de todos los grupos dado los nombres
	 * @param  roles
	 * @return List<Grupo> grupos
	 * @throws No dispara ninguna excepción.
	 */

	public List<Grupo> buscarTodos(String... roles) {
		return iGrupoDAO.findAll();
	}

	/**
	 * Buscar Grupo dado su nombre
	 * @param rol
	 * @return Objeto Grupo
	 * @throws NullPointerException si no
	 * encuentra el Grupo.
	 */
	
	public Grupo buscarGrupoNombre(String rol) {
		return iGrupoDAO.findByNombre(rol);
	}

	/**
	 * Busca una lista de todos los grupos a excepción el grupo de idGrupo 1
	 * @return List<Grupo> grupos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> listadoGrupo() {
		List<Grupo> GrupoLista = iGrupoDAO.findByEstatusTrueAndIdGrupoNot(1);
		return GrupoLista;
	}
	
	/**
	 * Busca una lista de los grupos dado el nombre del usuario al que pertenecen
	 * @param nombreUsuario
	 * @return List<Grupo> grupos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> listadoGrupoPerteneceUsuario(String nombreUsuario) {
		List<Grupo> listaGrupoPertenece = iGrupoDAO.buscarGruposPerteneceUsuario(nombreUsuario);
		return listaGrupoPertenece;
	}
	
	/**
	 * Busca una lista de los grupos dado el nombre del usuario al que NO pertenecen
	 * @param nombreUsuario
	 * @return List<Grupo> grupos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> listadoGrupoNoPerteneceUsuario(String nombreUsuario) {
		List<Grupo> listaGrupoNoPertenece = iGrupoDAO.buscarGruposNoPerteneceUsuario(nombreUsuario);
		return listaGrupoNoPertenece;
	}

	/**
	 * Elimina un grupo dado su id de grupo
	 * @param idGrupo
	 * @throws Dispara una excepción si el grupo no existe.
	 */
	
	public void eliminar(Integer idGrupo) {
		Grupo miGrupo = iGrupoDAO.findOne(idGrupo);
		miGrupo.setEstatus(false);
		iGrupoDAO.save(miGrupo);
	}

	/**
	 * Buscar Grupo filtrando por su nombre y descripción
	 * 
	 * @param nombre, descripcion
	 * @return List<Grupo> grupos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> buscarGrupoFiltro(String nombre, String descripcion) {
		List<Grupo> result = new LinkedList<Grupo>();
		if (nombre == null || descripcion == null) {// si el nombre es null o
													// descripcion es null,el resultado va a
													// ser la lista completa de
													// todos los s de motivo
			// si el codigo es null o vacio,el resultado va a ser la lista
			// completa de
			// todas los motivos
			result = listadoGrupo();
		} else {// caso contrario se recorre toda la lista y busca los s de
				// motivos con el nombre indicado en la caja de texto y tambien
				// busca todos los que tengan las letras iniciales de ese
				// nombre.
			for (Grupo grupo : listadoGrupo()) {
				if (grupo.getNombre().toLowerCase().contains(nombre.toLowerCase()) && grupo.getDescripcion().toLowerCase().contains(descripcion.toLowerCase())) {
						result.add(grupo);
				}
			}
		}
		return result;
	}
}
