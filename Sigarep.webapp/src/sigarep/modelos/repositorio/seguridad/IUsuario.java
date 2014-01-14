package sigarep.modelos.repositorio.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.seguridad.Usuario;

public interface IUsuario extends JpaRepository<Usuario, String> {

	@Query("Select usu FROM Usuario AS usu where estatus = TRUE")		
	public List<Usuario> buscarGruposActivos();
	
	//public Usuario findByIdUsuario(Integer IdUsuario);
}
