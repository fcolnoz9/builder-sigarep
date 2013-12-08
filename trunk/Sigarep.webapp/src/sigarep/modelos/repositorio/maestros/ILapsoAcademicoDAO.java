package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import sigarep.modelos.data.maestros.LapsoAcademico;

public interface ILapsoAcademicoDAO extends JpaRepository<LapsoAcademico, String> {

	@Query("select  pr from LapsoAcademico pr where pr.codigoLapso= :codigoLapso")
	public List<LapsoAcademico> buscar();
	@Query("select p from LapsoAcademico p where p.codigoLapso= :codigoLapso")
    public LapsoAcademico findBycodigoLapso(String codigoLapso);
	@Query("select p from LapsoAcademico p where p.codigoLapso= :codigoLapso")
	public List<LapsoAcademico> buscarLapsoAcademico(String codigoLapso);
	
	
	
}
