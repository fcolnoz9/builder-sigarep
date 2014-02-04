package sigarep.modelos.repositorio.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.seguridad.Grupo;

public interface IGrupo extends JpaRepository<Grupo, Integer> {

	public Grupo findByNombre(String rol);
	
	@Query("Select gru FROM Grupo AS gru where idGrupo != '1' AND estatus = TRUE")		
	public List<Grupo> buscarGruposActivos();
	
	@Query("SELECT DISTINCT g FROM UsuarioGrupo AS ug, Usuario AS u, Grupo AS g WHERE u.nombreUsuario = ug.id.nombreUsuario AND ug.id.idGrupo = g.idGrupo AND g.idGrupo != '1' AND u.nombreUsuario = :nombreUsuario")
	public List<Grupo> buscarGruposPerteneceUsuario(@Param("nombreUsuario") String nombreUsuario);

	@Query("SELECT DISTINCT g FROM UsuarioGrupo AS ug, Usuario AS u, Grupo AS g WHERE g.idGrupo NOT IN (SELECT DISTINCT g.idGrupo FROM UsuarioGrupo AS ug, Usuario AS u, Grupo AS g WHERE u.nombreUsuario = ug.id.nombreUsuario AND ug.id.idGrupo = g.idGrupo AND u.nombreUsuario = :nombreUsuario)") 
	public List<Grupo> buscarGruposNoPerteneceUsuario(@Param("nombreUsuario") String nombreUsuario);
}
