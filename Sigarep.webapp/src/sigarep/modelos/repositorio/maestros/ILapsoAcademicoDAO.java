package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.LapsoAcademico;

public interface ILapsoAcademicoDAO extends
		JpaRepository<LapsoAcademico, String> {

}
