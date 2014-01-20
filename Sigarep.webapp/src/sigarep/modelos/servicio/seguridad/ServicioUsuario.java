package sigarep.modelos.servicio.seguridad;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.zkoss.zhtml.Messagebox;

import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.repositorio.seguridad.IUsuario;

@Service("su")
public class ServicioUsuario {

	@Autowired
	private IUsuario iUsuario;

	public void guardarUsuario(Usuario usuario) {
		iUsuario.save(usuario);
	}
	
	public Usuario encontrarUsuario(String nombreusaurio){
		return iUsuario.findOne(nombreusaurio);
	}
	
	public void eliminar(String nombreusuario){
		Usuario miUsuario = iUsuario.findOne(nombreusuario);
		miUsuario.setEstatus(false);
		iUsuario.save(miUsuario);
	}
	
	public List<Usuario> listadoUsuario() {
		List<Usuario> usuarioLista = iUsuario.buscarGruposActivos();
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
	
	public boolean cambiarContrasena(String nombreUsuario, String contrasenaAnterior,
		String nuevaContrasena, String repetirContrasena) {
		Usuario usuario = encontrarUsuario(nombreUsuario);
		if(usuario==null){
			Messagebox.show("No existe el nombre de usuario que ha ingresado","Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);	}
		else
		{
		    if (usuario.getClave().equals(contrasenaAnterior)) {
				if (nuevaContrasena != null && nuevaContrasena.trim().length() > 0) {
					if (nuevaContrasena.equals(repetirContrasena)) {
						usuario.setClave(nuevaContrasena);
						guardarUsuario(usuario);
						return true;
					} else {
						Messagebox.show("Las nuevas contraseñas no coinciden",
							"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
					}
				} else {
					Messagebox.show("La nueva contraseña no puede estar vacia", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			} else {
				Messagebox.show("La contraseña anterior no coincide con la de el usuario", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);			
			}
		}
		return false;
	}
}
