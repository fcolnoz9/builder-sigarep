package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Operacion;

public interface IOperacionDAO extends JpaRepository<Operacion, Integer> {

}
