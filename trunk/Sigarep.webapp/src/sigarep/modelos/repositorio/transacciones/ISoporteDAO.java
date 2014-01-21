package sigarep.modelos.repositorio.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.transacciones.Soporte;

public interface ISoporteDAO extends JpaRepository<Soporte, Integer> {

}
