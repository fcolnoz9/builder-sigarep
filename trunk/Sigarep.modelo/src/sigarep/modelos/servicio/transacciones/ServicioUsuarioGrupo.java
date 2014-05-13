package sigarep.modelos.servicio.transacciones;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;
import sigarep.modelos.repositorio.transacciones.IUsuarioGrupoDAO;


/**
 * Clase ServicioUsuarioGrupo : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla UsuarioGrupo 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("serviciousuariogrupo")
public class ServicioUsuarioGrupo {
	
	// Atributos de la clase
	@PersistenceContext
	private EntityManager em;

	public @Autowired IUsuarioGrupoDAO iUsuarioGrupoDAO;
	
	/**
	 * Guarda un UsuarioGrupo en la tabla UsuarioGrupo
	 * @param usuariogrupo
	 * @throws No dispara ninguna excepción.
	 */
	public UsuarioGrupo guardar(UsuarioGrupo usuariogrupo) {
		return iUsuarioGrupoDAO.save(usuariogrupo);
	}
	
	/**
	 * Elimina lógicamente un usuariogrupo dado su id
	 * @param id
	 * @throws Dispara una excepción si el usuariogrupo a eliminar no existe.
	 */
	public void eliminar(UsuarioGrupoPK id){
		UsuarioGrupo miUsuarioGrupo = iUsuarioGrupoDAO.findOne(id);
		miUsuarioGrupo.setEstatus(false);
		iUsuarioGrupoDAO.save(miUsuarioGrupo);
	}
		
	/**
	 * Elimina físicamente un usuariogrupo de la tabla UsuarioGrupo dado su id.
	 * @param id
	 * @throws Dispara una excepción si el usuario a eliminar físicamente, no existe.
	 */
	public void eliminarFisicamente(UsuarioGrupoPK id){
		UsuarioGrupo miUsuarioGrupo = iUsuarioGrupoDAO.findOne(id);
		iUsuarioGrupoDAO.delete(miUsuarioGrupo);
	}
	
	/**
	 * Cuenta todos los usuarios grupos registrados
	 * @return Número de usuarios grupos contados
	 * @throws No dispara ninguna excepción.
	 */
	public int contarTodos() {
		return iUsuarioGrupoDAO.findAll().size();
	}

	/**
	 * Nuevo usuariogrupo registrado
	 * @return Objeto UsuarioGrupo
	 * @throws No dispara ninguna excepción.
	 */
	public UsuarioGrupo crear() {
		return new UsuarioGrupo();
	}
	
	/**
	 * Elimina un usuariogrupo por su id y por su nombre
	 * @param idGrupo, nombreUsuario
	 * @throws NullPointerException 
	 */
	public void eliminarUsuarioGrupo(Integer idGrupo, String nombreUsuario) 
	{
		String queryStatement = "delete from sigarep.usuario_grupo ug where " +
		"ug.id_grupo = '"+idGrupo +"' and ug.nombre_usuario = '"+nombreUsuario +"'";
		Query query = em.createNativeQuery(queryStatement);
		try {
			query.getSingleResult();
		} catch (Exception exp) {
			System.out.println("");
		}
	}
}
