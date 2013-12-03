package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.EnlaceIntere;

public interface IEnlaceInteresDAO extends JpaRepository<EnlaceIntere, Integer> {

}
