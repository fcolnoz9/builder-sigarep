package sigarep.modelos.repositorio.seguridad;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.seguridad.Grupo;
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
	
	/**
	 * Busca la totalidad de los roles del usuario cuyo nombre es parametro
	 * @param nombreUsuario Nombre del usuario al cual se le buscaran sus grupos
	 * @return List<Grupo> Lista de roles a los cuales pertenece el usuario
	 */
	@Query("SELECT DISTINCT g FROM UsuarioGrupo AS ug, Usuario AS u, Grupo AS g WHERE u.nombreUsuario = ug.id.nombreUsuario AND ug.id.idGrupo = g.idGrupo AND u.nombreUsuario = :nombreUsuario")
	public List<Grupo> totalidadRolesUsuario(@Param("nombreUsuario") String nombreUsuario);
	
}//Fin Clase IUsuarioDAO
