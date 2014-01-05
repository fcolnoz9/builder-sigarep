package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Banner;

public interface IBannerDAO extends JpaRepository<Banner, Integer> {
	@Query("select  bn from Banner bn where bn.estatus='true'")
	public List<Banner> listaBanner();
}
