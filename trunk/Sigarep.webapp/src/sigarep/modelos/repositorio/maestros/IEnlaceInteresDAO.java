package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.EnlaceInteres;

public interface IEnlaceInteresDAO extends JpaRepository<EnlaceInteres, Integer> {

}
