package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.Actividad;
import sigarep.modelos.data.maestros.PreguntaBasica;

public interface IActividadDAO extends JpaRepository<Actividad, Integer> {

	@Query("SELECT act FROM Actividad act WHERE act.nombre= :nombre")
	public List<Actividad> buscarActividad();

	@Query("SELECT act FROM Actividad act WHERE act.nombre= :nombre")
	public PreguntaBasica findBynombre(String nombre);

	@Query("SELECT act FROM Actividad act WHERE act.idActividad= :id_actividad")
	public List<PreguntaBasica> buscarActividadId(Integer id_actividad);

}
