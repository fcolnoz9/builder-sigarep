package sigarep.modelos.servicio.maestros;
import org.springframework.beans.factory.annotation.Autowired;

import sigarep.modelos.data.maestros.Usuario;
import sigarep.modelos.repositorio.maestros.IUsuarioDAO;
//+++++++++++++
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
//***********

@Service("serviciousuario")
public class ServicioUsuario {
private @Autowired IUsuarioDAO iUsuario;
	
	public ServicioUsuario() {
		// TODO Auto-generated constructor stub
	}

	public void guardar(Usuario usuario){
		iUsuario.save(usuario);
	}
	
	public void actualizar(Usuario usuario){
		
	}
	public void eliminar(String nombreusuario){
		Usuario miUsuario = iUsuario.findOne(nombreusuario);
		miUsuario.setEstatus(false);
		iUsuario.save(miUsuario);
	}
	
	public Usuario buscar(String nombreusaurio){
		return iUsuario.findOne(nombreusaurio);
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

}
