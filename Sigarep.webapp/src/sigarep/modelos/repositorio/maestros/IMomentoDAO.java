package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Momento;

public interface IMomentoDAO extends JpaRepository<Momento, Integer> {

}
