package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, String> {

	@Query("Select usu FROM Usuario AS usu where estatus = TRUE")		
	public List<Usuario> buscarGruposActivos();
	
}
