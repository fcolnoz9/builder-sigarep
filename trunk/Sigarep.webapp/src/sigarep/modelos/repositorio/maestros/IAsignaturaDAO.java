package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.Recaudo;
import sigarep.modelos.data.maestros.SancionMaestro;

public interface IAsignaturaDAO extends JpaRepository<Asignatura, String> {
	
	@Query("Select san FROM Asignatura AS san WHERE estatus = TRUE")
	public List<Asignatura> buscarAsignaturasActivas();

	@Query("SELECT a FROM Asignatura AS a WHERE estatus = 'TRUE' " +
			"AND a.programaAcademico.idPrograma = :idPrograma")
	public List<Asignatura> buscarAsignaturasPorPrograma(@Param("idPrograma")Integer idPrograma);
	
	@Query("Select a FROM Asignatura AS a WHERE a.nombreAsignatura = :nombreAsignatura")
	public Asignatura buscarAsignaturaPorNombre(@Param("nombreAsignatura") String nombreAsignatura);

}
