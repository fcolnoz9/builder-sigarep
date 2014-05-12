package sigarep.modelos.servicio.seguridad;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.repositorio.seguridad.IUsuarioDAO;

/**
* Clase ServicioUsuario Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla Usuario 
* @author Equipo Builder
* @version 1.0
* @since 15/12/2013
* @last 10/05/2014
*/

@Service("serviciousuario")
public class ServicioUsuario {
	// Atributos de la clase
	@Autowired
	private IUsuarioDAO iUsuarioDAO;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	/**
	 * Guarda un Usuario en la tabla Usuario
	 * @param usuario
	 * @throws No dispara ninguna excepción.
	 */
	
	public void guardarUsuario(Usuario usuario) {
		iUsuarioDAO.save(usuario);
	}
	
	/**
	 * Busca un usuario dado su nombre de usuario
	 * @param nombreUsaurio
	 * @return Objeto Usuario
	 * @throws Dispara una excepción si el usuario no existe.
	 */
	
	public Usuario encontrarUsuario(String nombreUsaurio){
		return iUsuarioDAO.findOne(nombreUsaurio);
	}
	
	/**
	 * Elimina lógicamente un usuario dado su nombre de usuario.
	 * @param nombreUsuario
	 * @throws Dispara una excepción si el usuario a eliminar no existe.
	 */
	
	public void eliminar(String nombreUsuario){
		Usuario miUsuario = iUsuarioDAO.findOne(nombreUsuario);
		miUsuario.setEstatus(false);
		iUsuarioDAO.save(miUsuario);
	}
	
	/**
	 * Elimina fisicamente un usuario de la tabla usuario dado su nombre de usuario.
	 * @param nombreUsuario
	 * @throws Dispara una excepción si el usuario a eliminar fisicamente, no existe.
	 */
	
	public void eliminarFisicamente(String nombreusuario){
		Usuario miUsuario = iUsuarioDAO.findOne(nombreusuario);		
		iUsuarioDAO.delete(miUsuario);
	}
	
	/**
	 * Busca una lista de los roles del usuario dado su nombre de usuario.
	 * @param nombreUsuario
	 * @return List<Grupo> roles del usuario.
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Grupo> rolesDelUsuario(String nombreUsuario) {
		List<Grupo> listaRolesUsuario = iUsuarioDAO.totalidadRolesUsuario(nombreUsuario);
		return listaRolesUsuario;
	}
	
	/**
	 * Busca una lista de todos los usuarios contenidos en la tabla Usuario con estatus activo.
	 * @return List<Usuario> lista de usuarios activos
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Usuario> listadoUsuario() {
		List<Usuario> usuarioLista = iUsuarioDAO.findByEstatusTrue();
		return usuarioLista;
	}
	
	/**
	 * Busca un Usuario filtrandolo por su nombre de usuario
	 * 
	 * @param nombreusuario
	 * @return List<Usuario> lista de usuarios.
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<Usuario> buscarUsuario(String nombreusuario) {
		List<Usuario> resultado = new LinkedList<Usuario>();
		if (nombreusuario == null || "".equals(nombreusuario)) {
			resultado = listadoUsuario();
		} else {
			for (Usuario usuario : listadoUsuario()) {
				if (usuario.getNombreUsuario().toLowerCase()
						.contains(nombreusuario.toLowerCase())) {
					resultado.add(usuario);
				}
			}
		}
		return resultado;
	}
	
	/**
	 * Cambia la contraseña del Usuario dado su nombre de usuario y nueva contraseña.
	 * 
	 * @param nombreUsuario, nuevaContrasena, confirmarContrasena
	 * @return true si la contraseña es cambiada y false en caso contrario.
	 * @throws Dispara excepción si el nombre del usuario no está registrado o si la nueva 
	 * contrasena y confirmar contrasena no coinciden.
	 */
	
	public boolean cambiarContrasena(String nombreUsuario,String nuevaContrasena, String confirmarContrasena) {
		Usuario usuario = encontrarUsuario(nombreUsuario);
		if (nuevaContrasena != null && nuevaContrasena.trim().length() > 0) {
				if (nuevaContrasena.equals(confirmarContrasena)) {
						usuario.setClave(nuevaContrasena);
						guardarUsuario(usuario);
						return true;
				} else mensajeAlUsuario.advertenciaContraseñasNoCoinciden();
		} else {
			mensajeAlUsuario.advertenciaContraseñaVacia();
		}			
		return false;
	}
}
