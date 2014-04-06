package sigarep.modelos.servicio.seguridad;

import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import sigarep.herramientas.MensajesAlUsuario;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.repositorio.seguridad.IUsuarioDAO;
import sigarep.modelos.repositorio.transacciones.IUsuarioGrupoDAO;

@Service("serviciousuario")
public class ServicioUsuario {

	@Autowired
	private IUsuarioDAO iUsuarioDAO;
	public IUsuarioGrupoDAO iUsuarioGrupoDAO;
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();
	
	public void guardarUsuario(Usuario usuario) {
		iUsuarioDAO.save(usuario);
	}
	
	public Usuario encontrarUsuario(String nombreusaurio){
		return iUsuarioDAO.findOne(nombreusaurio);
	}
	
	public void eliminar(String nombreusuario){
		Usuario miUsuario = iUsuarioDAO.findOne(nombreusuario);
		miUsuario.setEstatus(false);
		iUsuarioDAO.save(miUsuario);
	}
	public void eliminarFisicamente(String nombreusuario){
		Usuario miUsuario = iUsuarioDAO.findOne(nombreusuario);		
		iUsuarioDAO.delete(miUsuario);
	}
	
	public List<Grupo> rolesDelUsuario(String nombreUsuario) {
		List<Grupo> listaRolesUsuario = iUsuarioDAO.totalidadRolesUsuario(nombreUsuario);
		return listaRolesUsuario;
	}
	
	public List<Usuario> listadoUsuario() {
		List<Usuario> usuarioLista = iUsuarioDAO.findByEstatusTrue();
		return usuarioLista;
	}
	
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
	
	public boolean cambiarContrasena(String nombreUsuario,String nuevaContrasena, String repetirContrasena) {
		Usuario usuario = encontrarUsuario(nombreUsuario);
		if (nuevaContrasena != null && nuevaContrasena.trim().length() > 0) {
				if (nuevaContrasena.equals(repetirContrasena)) {
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
