package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import sigarep.modelos.data.maestros.LapsoAcademico;

public interface ILapsoAcademicoDAO extends JpaRepository<LapsoAcademico, String> {
	
	@Query("select  lapso from LapsoAcademico AS lapso  where estatus= TRUE")
	public List<LapsoAcademico> buscarActivoLapso();
	
	
	
}
