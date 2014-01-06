package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.PreguntaBasica;

public interface IActividadDAO extends JpaRepository<Actividad, Integer> {

	@Query("Select act FROM Actividad AS act WHERE estatus = TRUE")
	public List<Actividad> buscarActividadesActivas();

}
