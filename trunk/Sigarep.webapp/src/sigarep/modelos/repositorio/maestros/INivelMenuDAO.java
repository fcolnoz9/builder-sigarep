package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.NivelMenu;

public interface INivelMenuDAO extends JpaRepository<NivelMenu, Integer> {

}
