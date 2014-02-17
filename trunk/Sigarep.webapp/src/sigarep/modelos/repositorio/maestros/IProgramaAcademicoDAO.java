package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.ProgramaAcademico;

public interface IProgramaAcademicoDAO extends
		JpaRepository<ProgramaAcademico, Integer> {

	@Query("Select pro FROM ProgramaAcademico AS pro WHERE estatusPrograma = TRUE")
	public List<ProgramaAcademico> buscarProgramasActivos();
	
	@Query("SELECT COALESCE(MAX(pa.idPrograma),0) FROM ProgramaAcademico AS pa")
	public int buscarUltimoID();
}
