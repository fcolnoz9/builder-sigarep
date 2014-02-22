package sigarep.modelos.servicio.seguridad;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.zkoss.zhtml.Messagebox;

import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.repositorio.seguridad.IUsuarioDAO;

@Service("serviciousuario")
public class ServicioUsuario {

	@Autowired
	private IUsuarioDAO iUsuarioDAO;

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
				} else Messagebox.show("La nueva contraseña y la contraseña de confirmación no coinciden","Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		} else {
				Messagebox.show("La nueva contraseña no puede estar vacia", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		}			
		return false;
	}
}
