package sigarep.modelos.repositorio.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, String> {

	/**
	 * Busca los Usuarios que poseen estatus true
	 * @return List<Usuario> Lista de Usuarios con estatus true
	 */
	public List<Usuario> findByEstatusTrue();
	
	//public Usuario findByIdUsuario(Integer IdUsuario);
}
