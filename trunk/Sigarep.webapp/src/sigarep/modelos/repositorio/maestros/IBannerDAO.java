package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Banner;

public interface IBannerDAO extends JpaRepository<Banner, Integer> {

}
