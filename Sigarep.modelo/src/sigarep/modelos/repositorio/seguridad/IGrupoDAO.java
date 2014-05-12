package sigarep.modelos.repositorio.seguridad;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.seguridad.Grupo;

/**
* Repositorio IGrupoDAO : Repositorio JPA de la clase Grupo
*
* @author Equipo Builder
* @version 1.0
* @since 15/12/2013
* @last 10/05/2014
*/
public interface IGrupoDAO extends JpaRepository<Grupo, Integer> {

	/**
	* Busca un grupo por su nombre en la tabla Grupo
	*
	* @return Grupo grupo dado un nombre de grupo
	*/
	
	public Grupo findByNombre(String rol);
	
	/**
	* Busca todos los grupos en la tabla Grupo que tienen estatus true, exceptuando el grupo de id = idGrupo
	*
	* @return List<Grupo> Lista de grupos activos exceptuando el grupo de id = idGrupo
	*/
	public List<Grupo> findByEstatusTrueAndIdGrupoNot(Integer idGrupo);
	
	/**
	* Busca los grupos a los que pertenece un usuario exceptuando el grupo de cambio de contraseña
	*
	* @return List<Grupo> Lista de grupos al que pertenece el usuario
	*/
	@Query("SELECT DISTINCT g FROM UsuarioGrupo AS ug, Usuario AS u, Grupo AS g WHERE u.nombreUsuario = ug.id.nombreUsuario AND ug.id.idGrupo = g.idGrupo AND g.idGrupo != '1' AND u.nombreUsuario = :nombreUsuario")
	public List<Grupo> buscarGruposPerteneceUsuario(@Param("nombreUsuario") String nombreUsuario);
	
	/**
	* Busca los grupos a los que NO pertenece un usuario
	*
	* @return List<Grupo> Lista de grupos a los que NO pertenece el usuario
	*/
	@Query("SELECT DISTINCT g FROM UsuarioGrupo AS ug, Usuario AS u, Grupo AS g WHERE g.idGrupo != '1' AND g.idGrupo NOT IN (SELECT DISTINCT g.idGrupo FROM UsuarioGrupo AS ug, Usuario AS u, Grupo AS g WHERE u.nombreUsuario = ug.id.nombreUsuario AND ug.id.idGrupo = g.idGrupo AND u.nombreUsuario = :nombreUsuario)") 
	public List<Grupo> buscarGruposNoPerteneceUsuario(@Param("nombreUsuario") String nombreUsuario);

	/**
	 * Busca el ultimo id insertado en la tabla Grupo
	 * 
	 * @return int Ultimo id insertado en la tabla Grupo
	 */
	@Query("SELECT COALESCE(MAX(g.idGrupo),0) FROM Grupo AS g")
	public int buscarUltimoID();

}