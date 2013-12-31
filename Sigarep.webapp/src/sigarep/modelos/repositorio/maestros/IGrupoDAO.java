package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Grupo;

public interface IGrupoDAO extends JpaRepository<Grupo, Integer> {

	@Query("Select gru FROM Grupo AS gru where estatus = TRUE")		
	public List<Grupo> buscarGruposActivos();
}
