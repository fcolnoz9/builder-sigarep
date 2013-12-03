package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Reglamento;

public interface IReglamentoDAO extends JpaRepository<Reglamento, Integer> {

}
