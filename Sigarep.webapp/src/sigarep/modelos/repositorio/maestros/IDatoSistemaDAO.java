package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.DatoSistema;

public interface IDatoSistemaDAO extends JpaRepository<DatoSistema, Integer> {

}
