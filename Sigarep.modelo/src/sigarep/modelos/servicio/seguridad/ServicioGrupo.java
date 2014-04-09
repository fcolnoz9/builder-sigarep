package sigarep.modelos.servicio.seguridad;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.repositorio.seguridad.IGrupoDAO;
import sigarep.modelos.repositorio.transacciones.IUsuarioGrupoDAO;

@Service("serviciogrupo")
public class ServicioGrupo {

	@Autowired
	private IGrupoDAO iGrupoDAO;
	@Autowired
	private IUsuarioGrupoDAO iUsuarioGrupoDAO;

	/**
	 * Guardar Grupo
	 * @param Grupo grupo
	 * @return Guarda el objeto
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
	 * Buscar Grupo por identificador de grupo
	 * @param id del grupo
	 * @return Busca el grupo
	 * @throws No dispara ninguna excepción.
	 */
	
	public Grupo buscarGrupo(Integer idgrupo) {
		return iGrupoDAO.findOne(idgrupo);

	}
	
	/**
	 * Buscar listado de todos los grupos dado los nombres
	 * @param String roles
	 * @return Busca todos los grupos
	 * @throws No dispara ninguna excepción.
	 */

	public List<Grupo> buscarTodos(String... roles) {
		return iGrupoDAO.findAll();

	}

	/**
	 * Buscar Grupo por nombre del grupo
	 * @param nombre del grupo (rol)
	 * @return Busca el grupo
	 * @throws No dispara ninguna excepción.
	 */
	
	public Grupo buscarGrupoNombre(String rol) {
		return iGrupoDAO.findByNombre(rol);
	}

	/**
	 * Listado de los grupos
	 * @param
	 * @return Busca todos los grupos registrados con estatus true excepto el grupo de idGrupo = 1
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> listadoGrupo() {
		List<Grupo> GrupoLista = iGrupoDAO.findByEstatusTrueAndIdGrupoNot(1);
		return GrupoLista;
	}
	
	/**
	 * Listado de los grupos a los que pertenece el usuario (sus roles)
	 * @param Nombre del usuario
	 * @return Busca todos los grupos a los cuales está asociado el usuario
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> listadoGrupoPerteneceUsuario(String nombreUsuario) {
		List<Grupo> listaGrupoPertenece = iGrupoDAO.buscarGruposPerteneceUsuario(nombreUsuario);
		return listaGrupoPertenece;
	}
	
	/**
	 * Listado de los grupos a los que NO pertenece el usuario
	 * @param Nombre del usuario
	 * @return Busca todos los grupos a los cuales NO está asociado el usuario
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> listadoGrupoNoPerteneceUsuario(String nombreUsuario) {
		List<Grupo> listaGrupoNoPertenece = iGrupoDAO.buscarGruposNoPerteneceUsuario(nombreUsuario);
		return listaGrupoNoPertenece;
	}

	/**
	 * Eliminar Grupo
	 * @param Integer idGrupo
	 * @return Elimina lógicamente el objeto
	 * @throws No dispara ninguna excepción.
	 */
	
	public void eliminar(Integer idGrupo) {
		Grupo miGrupo = iGrupoDAO.findOne(idGrupo);
		miGrupo.setEstatus(false);
		iGrupoDAO.save(miGrupo);
	}

	/**
	 * Buscar Grupo filtrando por nombre y descripción
	 * 
	 * @param String nombre, String descripcion
	 * @return Busca una grupo por nombre y descripcion
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
