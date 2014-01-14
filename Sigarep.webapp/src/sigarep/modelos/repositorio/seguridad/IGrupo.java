package sigarep.modelos.repositorio.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.seguridad.Grupo;

public interface IGrupo extends JpaRepository<Grupo, Integer> {

	public Grupo findByNombre(String rol);
	
	@Query("Select gru FROM Grupo AS gru where estatus = TRUE")		
	public List<Grupo> buscarGruposActivos();
}
