package sigarep.modelos.repositorio.seguridad;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.seguridad.Usuario;

/**
 * Clase IUsuarioDAO 
 * @author BUILDER
 * @version 1.0
 * @since 15/12/2013
 */
public interface IUsuarioDAO extends JpaRepository<Usuario, String> {

	/**
	 * Busca los Usuarios que poseen estatus true
	 * @return List<Usuario> Lista de Usuarios con estatus true
	 */
	public List<Usuario> findByEstatusTrue();
	
}//Fin Clase IUsuarioDAO
