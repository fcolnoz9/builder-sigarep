package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Grupo;

public interface IGrupoDAO extends JpaRepository<Grupo, Integer> {

}
