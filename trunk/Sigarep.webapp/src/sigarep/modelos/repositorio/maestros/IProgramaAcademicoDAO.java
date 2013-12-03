package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.ProgramaAcademico;

public interface IProgramaAcademicoDAO extends
		JpaRepository<ProgramaAcademico, Integer> {

}
